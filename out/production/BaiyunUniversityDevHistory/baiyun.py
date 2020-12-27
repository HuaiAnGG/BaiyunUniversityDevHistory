# -*- coding: UTF-8 -*-

import requests
from bs4 import BeautifulSoup
import pymysql
import time

if __name__ == '__main__':
    url = 'http://www.baiyunu.edu.cn/html/cn/2019dsj/';
    response = requests.get('http://www.baiyunu.edu.cn/html/cn/2019dsj/')
    # print(response.encoding)
    response.encoding = 'utf-8'
    # print(response.text)
    soup = BeautifulSoup(response.content, "html.parser")

    # 连接数据库
    db = pymysql.connect('localhost', 'root', '123456', 'db_baiyun')
    print('正在清空数据表已存在分类、校史数据....')
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    cursor.execute("SET FOREIGN_KEY_CHECKS = 0")
    cursor.execute("truncate table category")
    cursor.execute("truncate table article")
    cursor.execute("SET FOREIGN_KEY_CHECKS = 1")
    db.commit()
    print('分类、校史数据已经清空~！')
    print('正在爬取网站(http://www.baiyunu.edu.cn/html/cn/2019dsj/)内容，请稍后...')
    # 获取所有年份
    year_all_li_html = soup.find_all('li', attrs={'class': 'se'})
    # 获取年份名字
    year_title = soup.find(attrs={'class': 'article-title'})
    # 去除头两个
    year_all_li_html.remove(year_all_li_html[0])
    year_all_li_html.remove(year_all_li_html[0])
    year_all_li_html.remove(year_all_li_html[0])

    # print(year_all_li_html, '\n\n\n')
    year_all_a_html = {}
    # 插入第一个
    year_all_a_html['2019年'] = url
    # 插入剩下的年份
    for item in year_all_li_html:
        # print(item)
        a = item.find('a')
        # print(a)
        year_all_a_html[a.text] = str('http://www.baiyunu.edu.cn' + a.get('href'))
    # 把年份存放到数据库中
    for year_name, year_url in year_all_a_html.items():
        cursor.execute("insert into category(cid, cname, parentId, url) values('%s', '%s', '%d', '%s')" % (
            year_name.replace('年', ''), year_name, 0, year_url))
    db.commit()

    article_count = 0;
    for year_name, year_url in year_all_a_html.items():
        # 所有年份文章数据
        article_dict = {}
        if soup.find(attrs={'class': 'text-center mt15'}) is None:
            # print(soup)
            # year_title = soup.find(attrs={'class': 'article-title'})
            # print(article_content.text)
            year_content = soup.find(attrs={'class': 'article-cont'})
            # print(article_content)
            # 月份字典
            month_dict = {}
            # 设置到字典中
            article_content = soup.find(attrs={'class': 'article-title'})
            # 标题
            article_title = article_content.text
            # 按月份分类
            article_content = soup.find(attrs={'class': 'article-cont'})
            # article_dict[article_title] = article_content
            # 按月份分类字典
            # print(article_content, '\n\n\n')
            # 替换所有 img 地址：'/upfile' -> 'http://www.baiyunu.edu.cn/upfile'
            for img in article_content.find_all('img'):
                img['src'] = img['src'].replace('http://www.baiyunu.edu.cn/upfile', '/upfile') \
                    .replace('/upfile', 'http://www.baiyunu.edu.cn/upfile')

            month_title = article_content.find('strong').text
            month_dict[month_title] = article_content
            article_dict[year_name] = month_dict

        else:
            page_list_html = soup.find(attrs={'class': 'text-center mt15'})
            # page_list = page_list_html.find_all('a', attrs={'class': 'btn btn-default'})
            # print(page_list)
            page_list = page_list_html.find_all('a', attrs={'class': 'btn btn-default'})
            page_list_url = [url]
            for herf in page_list:
                page_list_url.append('http://www.baiyunu.edu.cn/' + herf.get('href'))

            # 月份字典
            month_dict = {}
            # 装载每个月的文章
            for _url in page_list_url:
                # print(_url)
                response = requests.get(_url)
                # print(response.encoding)
                response.encoding = 'utf-8'
                # print(response.text)
                soup = BeautifulSoup(response.content, "html.parser")
                # 设置到字典中
                article_content = soup.find(attrs={'class': 'article-title'})
                # 标题
                article_title = article_content.text
                # 按月份分类
                article_content = soup.find(attrs={'class': 'article-cont'})
                # article_dict[article_title] = article_content
                # 按月份分类字典
                # print(article_content, '\n\n\n')
                # 替换所有 img 地址：'/upfile' -> 'http://www.baiyunu.edu.cn/upfile'
                for img in article_content.find_all('img'):
                    img['src'] = img['src'].replace('http://www.baiyunu.edu.cn/upfile', '/upfile') \
                        .replace('/upfile', 'http://www.baiyunu.edu.cn/upfile')

                month_title = article_content.find('strong').text
                month_dict[month_title] = article_content
                article_dict[year_name] = month_dict
        p_count = 1;
        for key1, val1 in article_dict.items():
            # 添加年份
            # cursor.execute("insert into tb_year values (null, '%s')" % key1)
            for key2, val2 in val1.items():
                article_count += 1
                article_desc_text = val2.find_all('p')[1].text
                if len(article_desc_text) > 50:
                    article_desc_text = str(article_desc_text[0:50] + '...')
                # 使用 execute()  方法执行 SQL 查询
                sqlStr = str(
                    "insert into article(article_title, article_desc, article_content, article_time, article_cid) values('%s', '%s','%s', '%d','%s')" % (
                        key2, article_desc_text, val2, int(round(time.time() * 1000)), year_name.replace('年', '')))
                cursor.execute(sqlStr)
                print("'%s' '%s' ：'%s')" % (year_name, key2, article_desc_text))
            db.commit()

    print('数据爬取完成，共 %d 条数据' % article_count)
    cursor.close()
    db.close()
