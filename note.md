# 更新记录：

## 2022.6.27

### 1、增加了如下接口

```
http://localhost:8081/api/hello
请求方法：GET
请求参数：无
返回参数：

```

```
http://localhost:8081/api/hello_id?id=777
请求方法：GET
请求参数：id，以查询的方式给出
返回参数：
```

```
http://10.133.46.17:8081/api/hello_post_new
请求方法：POST
请求参数：page_num，整数
返回参数：{"msg":{"content":"{\"page_num\":\"1\"}"},"traceNo":"","resultCode":"200","resultMsg":"成功"}
```









接口：/hello

返回：

|-msg:{}

|-resultMsg:

|-traceNo:

|-resultCode: