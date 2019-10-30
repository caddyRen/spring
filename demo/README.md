#h3流程运行接口
1. 获取可发起的流程 GET
    - 问题：返回值中ObjectID不知是什么id，不知具体用在什么接口上 
    - url http://192.168.43.230:8088/Portal/bpm-api/workfownodes/administrator?systemCode=H3&secret=Authine&showFavorite=false&isMobile=false&userCode=administrator
    ```json
    {
        "code": 0,
        "msg": null,
        "data": [
            {
                "children": null,
                "Code": "20190917apitest",
                "ObjectID": "9549e18f-7b51-43ef-8a9f-948217f8927f",
                "IsLeaf": true,
                "IconFileName": null,
                "Frequent": 0,
                "PublishedTime": "2019/09/17",
                "Icon": "icon-liuchengmoxing",
                "Version": 4,
                "DisplayName": "api测试"
            }
        ]
    }
    ```
2. 发起流程 POST
    - 问题：
        - 返回值中instanceId不知是什么id，不知具体用在什么接口上 
        - 参数中paramValues是发起流程时填写工单内容的表单的名称，iteamName不知道从什么接口获取，
    - url http://192.168.43.230:8088/Portal/bpm-api/workflows/20190917apitest
    - 参数
    ```json
    {
    "systemCode":"H3",
    "secret":"Authine",
    "workflowCode":"20190917apitest",
    "userCode":"administrator",
    "finishStart":"true",
    "paramValues":[
    {"itemName":"id","itemValue":"002"},
    {"itemName":"createdate","itemValue":"2019-09-17 11:11:11"},
    {"itemName":"detail","itemValue":"测试哈"}]
    }
    ```
    - 返回值
    ```json
     {
         "code": 0,
         "msg": null,
         "data": {
             "instanceId": "43e12f72-e358-4a7b-84c0-6c541c92dfd3",
             "workItemID": null
         }
     }
    ``` 
3. 获取待办 GET
  - 问题：
        - 参数中userID=d68f8e44-eccd-426b-b3b9-94ffe8c34213是用户表对应的用户objectId，不是用户名
  - url http://192.168.43.230:8088/Portal/bpm-api/workitems/unfinish/d68f8e44-eccd-426b-b3b9-94ffe8c34213?systemCode=H3&secret=Authine&startTime=2019-09-01&endTime=2019-09-30&startIndex=1&endIndex=1
      - 参数
      ```userID?systemCode=H3&secret=Authine&startTime=2019-01-21&endTime=2019-02-01&startIndex=1&endIndex=1
      ```
      - 返回值
      ```json
       {
           "code": 0,
           "msg": null,
           "data": [
               {
                   "instanceCreatedTime": "2019-09-26 10:55:55",
                   "instanceState": "2",
                   "instanceSequenceNo": "201920190917apitest0000000018",
                   "ItemCount": null,
                   "WorkflowCode": "20190917apitest",
                   "ActivityCode": "Activity2",
                   "ParticipantName": "任乾坤",
                   "InstanceId": "43e12f72-e358-4a7b-84c0-6c541c92dfd3",
                   "Participant": "d68f8e44-eccd-426b-b3b9-94ffe8c34213",
                   "InstanceName": "api测试.4",
                   "ReceiveTime": "2019-09-26 10:55",
                   "FinishTime": "1970-01-01 00:00:00",
                   "Originator": "18f923a7-5a5e-426d-94ae-a55ad1a4b239",
                   "OriginatorName": "系统管理员",
                   "StayTime": {
                       "Days": 2914365,
                       "Ticks": 2518011827650000000,
                       "Milliseconds": 0,
                       "Hours": 12,
                       "Minutes": 59,
                       "Seconds": 25
                   },
                   "PlanFinishTime": "9998-12-31 00:00:00",
                   "ItemSummary": "",
                   "Urged": false,
                   "Consulted": false,
                   "ConsultantFinished": false,
                   "Assisted": false,
                   "AssistantFinished": false,
                   "OriginatorOUName": "我的公司",
                   "WorkflowName": "api测试",
                   "DisplayWorkflowCode": false,
                   "CirculateCreator": "null",
                   "CirculateCreatorName": "",
                   "State": 0,
                   "DisplayName": "一次审批",
                   "Priority": "1",
                   "ObjectID": "782c9936-7c59-457c-9182-341b441e23cc"（审核流程的workItemId会用到）
               }
           ]
       }
       ``` 
4. 审核节点需要填表单（可选） PUT
    - 问题: 参数 bizObjectId，不知从什么接口获取，目前查表能获取，但是没找到能where的过滤条件
    - url http://192.168.43.230:8088/Portal/bpm-api/itemvalues/spyj1
    - 参数
    ```json
    {"systemCode":"H3",
    "secret":"Authine",
    "userId":"d68f8e44-eccd-426b-b3b9-94ffe8c34213",
    "bizObjectSchemaCode":"20190917apitest",
    "bizObjectId":"a3e49682-8b1d-4ffb-a922-2eba1c279823",
    "keyName":"spyj1","keyValue":"awsl"}
    }
    ```
    - 返回值
    ```json
     {
         "code": 0,
         "msg": null,
         "data": null
     }
    ``` 
5. 提交审核通过 PUT
    - 问题：
        - 参数是userId不是userName
    - url http://192.168.43.230:8088/Portal/bpm-api/workitems/submit/ab30e466-7f57-4ef9-9a27-c35410f620a8
    - 参数
    （workItemId是获取待办接口返回值里的ObjectID）
    ```json
    {"systemCode":"H3",
     "secret":"Authine",
     "userId":"d68f8e44-eccd-426b-b3b9-94ffe8c34213",
     "workItemId":"782c9936-7c59-457c-9182-341b441e23cc",
     "commentText":"同意"}
    ```
    - 返回值
    ```json
     {
         "code": 0,
         "msg": null,
         "data": null
     }
    ``` 
