package com.ysovo.yinsblog.controller;


import com.ysovo.yinsblog.service.UserAuthService;
import com.ysovo.yinsblog.vo.Result;
import com.ysovo.yinsblog.vo.UserVO;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * (UserAuth)表控制层
 *
 * @author ys
 * @since 2023-03-26 13:31:12
 */

@RestController
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;


    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return {@link Result<>}
     */

    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody UserVO user) {
        userAuthService.register(user);
        return Result.ok();
    }

    @GetMapping("/xx")
    public Result<?> xx() {
        return Result.ok("test");
    }



}

