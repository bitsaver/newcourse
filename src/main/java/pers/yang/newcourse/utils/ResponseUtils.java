package pers.yang.newcourse.utils;

import pers.yang.newcourse.entity.Response;


public class ResponseUtils {

		private static final String SUCCESS = "操作成功！";

		public static Response success(Object obj){
				Response res = new Response();
				res.setCode(200);
				res.setData(obj);
				res.setMsg(SUCCESS);
				return res;
		}

		public static Response success(){
				return success(null);
		}

		public static Response error(Integer code, String msg){
				Response res = new Response();
				res.setCode(code);
				res.setMsg(msg);
				return res;
		}

}