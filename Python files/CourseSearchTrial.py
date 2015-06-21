import json
from bs4 import BeautifulSoup
import urllib
import re

htmltext=open("MTH102A.txt","r").read()
soup=BeautifulSoup(htmltext)
x=soup.find_all("table",{"border":"0"})
y=x[0].find_all("tr")
last=len(y)
lst=[]

z=y[0].find_all("td")
dict={"Course No.":z[1].text}
z=y[1].find_all("td")
dict["Course Title"]=z[1].text
z=y[2].find_all("td")
dict["Instructor"]=z[1].text
z=y[3].find_all("td")
dict["Email"]=z[1].text
z=y[4].find_all("td")
dict["Pre-Requisites"]=z[1].text
z=y[5].find_all("td")
dict["Units"]=z[1].text
z=y[6].find_all("td")
dict["Department"]=z[1].text
z=y[7].find_all("td")

t=z[1].text.replace("\n","")
t1=t.replace("\r","")
t2=t1.replace("\t","")
dict["Schedule"]=t2
z=y[8].find_all("td")
dict["Instructor's Notes"]=z[1].text
z=y[9].find_all("td")
#for item in z[1]
t=z[1].findAll("script")[0]

dict["Current Status"]=t.string
z=y[10].find_all("td")
dict["Total Registration"]=z[1].text
lst.append(dict)


jso=json.dumps(lst)
print jso
