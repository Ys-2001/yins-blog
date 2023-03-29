package com.ysovo.yinsblog.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ysovo.yinsblog.constant.RedisPrefixConst;
import com.ysovo.yinsblog.dto.UserDetailDTO;
import com.ysovo.yinsblog.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @className: TokenManager
 * @description: jwt令牌管理：
 * JWT实现大体思路：
 * 根据UUID生成一个JWT——token，此 token 并不会设置过期时间，只做数据的记录
 * （缓存前缀 + UUID） 作为 Redis 缓存对象的 key，同时 UUID 又是保存在 token 中的，所以 token 也就与 Redis关联了起来
 * 只有通过 token 获取到 UUID后，才能在 Redis 中获取到登录对象，如果没有 token，也就获取不到对象，则认证失败，否则成功
 * @author: LiJunYi
 * @create: 2022/7/27 11:16
 */
@Component
@Slf4j
public class TokenManager
{
    /**
     * 令牌自定义标识
     */
    @Value("${token.header}")
    private String header;

    /**
     * 令牌秘钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * 令牌有效期（默认30分钟）
     */
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    private final RedisService redisCache;

    public TokenManager(RedisService redisCache) {
        this.redisCache = redisCache;
    }


    /**
     * 从 request 中获取token
     * 通过解析token获取用户在redis中缓存的key从而获取到登录用户信息
     *
     * @param request 请求
     * @return {@link UserDetailDTO}
     */
    public UserDetailDTO getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StrUtil.isNotEmpty(token))
        {
            try
            {
                Claims claims = parseToken(token);
                // 获取用户ID
                String userId = (String) claims.get("userId");
                // 获取 Redis 缓存中的 key
                String userKey = getTokenKey(userId);
                UserDetailDTO userDetailDTO=JSON.parseObject(redisCache.get(userKey).toString(), UserDetailDTO.class);
                return userDetailDTO;
            }
            catch (Exception ignored)
            {

            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(UserDetailDTO loginUser)
    {
        if (ObjectUtil.isNotNull(loginUser) && StrUtil.isNotEmpty(loginUser.getToken()))
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除令牌
     *
     * @param token 令牌
     */
    public void removeToken(String token)
    {
        if (StrUtil.isNotEmpty(token))
        {
            String userId = getUserIdFromToken(token);
            String userKey = getTokenKey(userId);
            redisCache.del(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 登录用户
     * @return {@link String}
     */
    public String createToken(UserDetailDTO loginUser)
    {
        String userId = loginUser.getId().toString();
        String uuid = IdUtil.fastUUID();

        // 将用户ID和UUID组合成唯一标识符，作为JWT的唯一标识符
        String jwtId = userId + ":" + uuid;
        loginUser.setToken(jwtId);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>(8);
        claims.put("userId", userId);
        return createToken(jwtId, claims);
    }


    /**
     * 验证令牌有效期，自动刷新缓存
     *
     * @param loginUser 登录用户
     */
    public void verifyToken(UserDetailDTO loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 创建token时存入当前登录用户并同时刷新令牌过期时间
     *
     * @param loginUser 登录用户
     */
    public void refreshToken(UserDetailDTO loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);

        String token = loginUser.getToken();
        if (StrUtil.isNotEmpty(token))
        {
            String userId = getUserIdFromToken(token);
            String userKey = getTokenKey(userId);
            if (redisCache.hasKey(userKey)){
                redisCache.expire(userKey,expireTime*60);
            }
            redisCache.set(userKey, JSON.toJSONString(loginUser), expireTime*60);
        }
    }

    /**
     * 创建令牌
     *
     * @param jwtId  JWT的唯一标识符
     * @param claims 数据声明
     * @return {@link String}
     */
    public String createToken(String jwtId, Map<String, Object> claims)
    {
        return Jwts.builder()
                .setId(jwtId)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取令牌
     *
     * @param request 请求
     * @return {@link String}
     */
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        if (StrUtil.isNotEmpty(token) && token.startsWith(RedisPrefixConst.USER_TOKEN))
        {
            token = token.replace(RedisPrefixConst.USER_TOKEN, "");
        }
        return token;
    }

    /**
     * 从令牌中获取用户ID
     *
     * @param token 令牌
     * @return 用户ID
     */
    private String getUserIdFromToken(String token)
    {
        String[] parts = token.split(":");
        if (parts.length == 2)
        {
            return parts[0];
        }
        else
        {
            return null;
        }
    }

    /**
     * 获取令牌在缓存中的key
     *
     * @param userId 用户ID
     * @return {@link String}
     */
    private String getTokenKey(String userId)
    {
        return RedisPrefixConst.USER_LOGIN_TOKEN + userId;
    }
}
