import baseAxios from "./BaseAxios";

export const addProduct = async (orderDetailDTO, productDetailDTO, token) => {
    try {
        const res = await baseAxios.post(
            `/api/order/create`,
            orderDetailDTO, productDetailDTO,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            }
        );
        return { success: true, data: res.data };
    } catch (error) {
        if (error.response) {
            console.log(error.response.data);
            return { success: false, data: error.response.data };
        } else {
            console.log(error.response.data);
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }
    }
};

export const deleteCart = async (cartId, token) => {
    try {
        console.log("hehehehe");

        const res = await baseAxios.post(
            `/api/order/cart`, cartId,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            }
        );
        return { success: true, data: res.data };
    } catch (error) {
        console.log(error);
        if (error.response) {
            console.log(error.response.data);
            return { success: false, data: error.response.data };
        } else {
            console.log(error.response.data);
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }
    }
};