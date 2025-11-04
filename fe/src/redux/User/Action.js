import baseAxios from "../../service/BaseAxios";
import { CURRENT_USER, SET_TOKEN, SET_USER_PRINCIPAL } from "./ActionType";


export const currentUser = () => async (dispatch) => {
    try {
        // BaseAxios sẽ tự động thêm token từ localStorage
        const res = await baseAxios.get(`/api/users/profile`);
        console.log("res", res.data);

        dispatch({ type: CURRENT_USER, payload: res.data });
        return { success: true, payload: res.data };
    } catch (error) {
        console.log("Lỗi khi lấy thông tin user:", error);
        if (error.response) {
            return { success: false, data: error.response.data }
        } else {
            return { success: false, data: "Lỗi ở server vui lòng truy cập lại" }
        }
    }
};

export const setToken = (token) => {
    return { type: SET_TOKEN, payload: token };
};

export const setUserPrincipal = (userPrincipal) => {
    return { type: SET_USER_PRINCIPAL, payload: userPrincipal };
};