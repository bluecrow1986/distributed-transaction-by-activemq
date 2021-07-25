package com.bluecrow.core.util;

import com.bluecrow.core.entity.ResponseBo;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpClientHelper {

	public static ResponseBo doPost(String url, MultiValueMap<String, String> params){
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpMethod method = HttpMethod.POST;
		// 以表单的方式提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//将请求头部和参数合成一个请求
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
		//执行HTTP请求，将返回的结构使用ResponseBo类格式化
		ResponseEntity<ResponseBo> response = client.exchange(url, method, requestEntity, ResponseBo.class);

		return response.getBody();
	}
}
