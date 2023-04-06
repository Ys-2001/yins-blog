import dayjs from "dayjs";

export const formDate = (date: Date) => {
    return dayjs(date).format("YYYY-MM-DD")
}

export const formDateFullTime = (date: Date) => {
    return dayjs(date).format("YYYY-MM-DD HH:mm:ss")
}

export const formDateYear = (date: Date) => {
    return dayjs(date).format("YYYY")
}

