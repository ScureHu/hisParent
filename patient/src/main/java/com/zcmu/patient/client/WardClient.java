package com.zcmu.patient.client;


import org.springframework.cloud.openfeign.FeignClient;

/**
 * bq客户端
 */
@FeignClient("his-ward")
public interface WardClient {

}
