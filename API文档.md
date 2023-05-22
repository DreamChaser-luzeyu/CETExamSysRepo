---
title: CetExamSys v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.17"

---

# CetExamSys

> v1.0.0

Base URLs:

# 登录相关API

## POST 学生登录

POST /LoginController/studentLogin

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userName|query|string| 否 |none|
|password|query|string| 否 |none|

> 返回示例

> 成功

```json
{
  "respCode": 20000,
  "feedback": "success",
  "message": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» respCode|integer|true|none||none|
|» feedback|string|true|none||none|
|» message|string¦null|true|none||none|

## GET 退出登录

GET /LoginController/logout

> 返回示例

> 成功

```json
{
  "respCode": 20000,
  "feedback": "success",
  "message": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» respCode|integer|true|none||none|
|» feedback|string|true|none||none|
|» message|string¦null|true|none||none|

## POST 教师登录

POST /LoginController/teacherLogin

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userName|query|string| 否 |none|
|password|query|string| 否 |none|

> 返回示例

> 成功

```json
{
  "respCode": 20000,
  "feedback": "success",
  "message": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» respCode|integer|true|none||none|
|» feedback|string|true|none||none|
|» message|string¦null|true|none||none|

# 学生管理相关API

## GET 报名考试

GET /StuManagement/applyExam

> 返回示例

> 成功

```json
{
  "respCode": 20000,
  "feedback": "success",
  "message": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» respCode|integer|true|none||none|
|» feedback|string|true|none||none|
|» message|string¦null|true|none||none|

## POST 取消报名考试

POST /StuManagement/cancelExam

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userName|query|string| 否 |none|

> 返回示例

> 成功

```json
{
  "respCode": 20000,
  "feedback": "success",
  "message": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» respCode|integer|true|none||none|
|» feedback|string|true|none||none|
|» message|string¦null|true|none||none|

## POST 学生注册

POST /StuManagement/registerStudent

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userName|query|string| 否 |none|
|password|query|string| 否 |none|
|realName|query|string| 否 |none|

> 返回示例

> 成功

```json
{
  "respCode": 20000,
  "feedback": "success",
  "message": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» respCode|integer|true|none||none|
|» feedback|string|true|none||none|
|» message|string¦null|true|none||none|

# 考试相关API

## GET 获得所有作答

GET /ExamController/getAllAnswers

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 作答选择题

POST /ExamController/answerChoiceQuestion

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|choiceQuestionAnswer|query|string| 否 |选择题答案|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 作答主观题

POST /ExamController/answerSubjectiveQuestion

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|subjectiveQuestionAnswer|query|string| 否 |主观题答案|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 数据模型

<h2 id="tocS_Student">Student</h2>

<a id="schemastudent"></a>
<a id="schema_Student"></a>
<a id="tocSstudent"></a>
<a id="tocsstudent"></a>

```json
{
  "userName": "string",
  "realName": "string",
  "password": "string",
  "hasApplied": 0
}

```

学生用户

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|userName|string|true|none|用户名（唯一）|none|
|realName|string|true|none|真实姓名|none|
|password|string|true|none|密码|none|
|hasApplied|integer|true|none|是否报名|none|

