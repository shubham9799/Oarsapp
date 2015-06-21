
import json
from bs4 import BeautifulSoup
import urllib
import re
f=open("titles.txt","r").read()
f=f.split("\n")
lst=[]
for sub in f:
    htmltext = urllib.urlopen("http://oars.cc.iitk.ac.in:5050/Utils/CourseInfoPopUp.asp?Course="+sub).read()
    soup=BeautifulSoup(htmltext)
    x=soup.find_all("table",{"border":"0"})
    
    y=x[0].find_all("tr")
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
    dict["LEC:DAY"]="none"
    dict["LEC:TIME"]="none"
    dict["LAB:TIME"]="none"
    dict["LAB:DAY"]="none"
    dict["T/D:DAY"]="none"
    dict["T/D:TIME"]="none"
    t=z[1].text.replace("\n","")
    t1=t.replace("\r","")
    t2=t1.replace("\t","")
    t2=t2.split(";")
    for i in range(0,len(t2)-1):
        t2[i]=t2[i].split(" ")
        dict[t2[i][0]+""+"DAY"]=t2[i][1]
        dict[t2[i][0]+"TIME"]=t2[i][2]
        #dict[t2[i][0]+"LOCATION"]=t2[i][3]

    #dict["Schedule"]=t2

    z=y[8].find_all("td")
    dict["Instructor's Notes"]=z[1].text

    #z=y[9].find_all("td")

    #p = z[1].findAll('script')
    #print p[0].next_sibling.text
    #t=z[1].findAll("script")[0]
    #p=t.nextSibling
    #dict["Current Status"]=p.nextSibling


    z=y[10].find_all("td")
    dict["Total Registration"]=z[1].text

    lst.append(dict)


jso=json.dumps(lst)
f=open("course_search.txt","w")
f.write(jso)
f.close
print jso

