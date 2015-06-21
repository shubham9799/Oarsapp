import mechanize
import cookielib
import json
from bs4 import BeautifulSoup
import urllib
import re

br=mechanize.Browser()
br.set_handle_robots(False)


cj = cookielib.LWPCookieJar()
br.set_cookiejar(cj)
f=open("titles.txt","w")

br.addheaders=[("User-agent","Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko")]
br.open("http://oars.cc.iitk.ac.in:5050/common/CourseSearch.asp")
br.select_form(name="CourseSearchForm")
br["Number"]=""
br["Title"]=""
br["Instructor"]=""
br["Email"]=""
br["Dept"]=""
response = br.submit()
content = response.read()

lst=[]
#htmltext = urllib.urlopen("http://oars.cc.iitk.ac.in:5050/common/CourseSearch.asp").read()
soup=BeautifulSoup(content)
x=soup.find_all("table",{"border":"1"})
y=x[0].find_all("tr")
for i in range(1,len(y)):
    z=y[i].find_all("td")
    p=z[0].find_all("font")
    q=p[0].find_all("a")
    f.write(q[0].text+"\n")
    
