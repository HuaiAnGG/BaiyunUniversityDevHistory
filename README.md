"# BaiyunUniversityDevHistory" 

> Python 爬虫获取[广东白云学院大事件](http://www.baiyunu.edu.cn/html/cn/2019dsj/) 通过网页展示，后端使用 Servlet + JSP

## 食用教程

#### 1.	需要先部署python項目：[BaiYunDevHist.py](https://github.com/HuaiAnGG/BaiyunUniversityDevHistory/blob/master/resource/baiyun.py)

##### [BaiYunDevHist.py](https://github.com/HuaiAnGG/BaiyunUniversityDevHistory/blob/master/resource/baiyun.py) 需要依赖：

- requests	（網絡請求）
- BeautifulSoup4 或者 beautifulSoup  （xml解析）
- pymysql  （數據庫連接池）
##### 修改 python-mysql 連接池對象
- db = pymysql.connect( host, username, passowrd, db_name)
#### 2. 數據庫需要先創建 [article、category 兩張表](https://github.com/HuaiAnGG/BaiyunUniversityDevHistory/blob/master/resource/db_baiyun.sql) ，表結構如下：
- article：白雲學院大事件數據表，結構如下：
```sql
  -- ----------------------------
  -- Table structure 
