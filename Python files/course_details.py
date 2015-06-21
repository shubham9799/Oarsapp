import mechanize
import cookielib
import json
from bs4 import BeautifulSoup
br=mechanize.Browser()
br.set_handle_robots(False)


cj = cookielib.LWPCookieJar()
br.set_cookiejar(cj)


br.addheaders=[("User-agent","Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko")]
sign_in=br.open("http://oars2.cc.iitk.ac.in:6060/login.asp")
br.select_form(nr=0)
br["LoginID"]="14016"
br["Password"]="14016"
logged_in=br.submit()
logincheck=logged_in.read()

#curr_plan=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/reg_plan.asp?menu=101")
#acad_info=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/Default.asp")
#trans=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/Transcript.asp?menu=91")
#backlog=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/BacklogView.asp?menu=101")
#grade=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/studentgrdcpi.asp?menu=91")
#fin_reg=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/Afteradd_dropStatus.asp?menu=95")
#pre_reg=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/BeforeStatus.asp?menu=95")
time_table=br.retrieve("http://oars2.cc.iitk.ac.in:6060/Student/reg_plan_new.asp")

fil=open(time_table[0],"r")
html=fil.read()

soup=BeautifulSoup(html)
x=soup.find_all("table",{"width":"95%"})
y=x[0].find_all("tr")
last = len(y)
for i in range(2,last-1):
    z=y[i].find_all("td")
    #for items in z:
        #print items.contents
    #print '\n'
lst=[]
for j in range(2,last-1):
    z=y[j].find_all("td")
    dic={'End Sem':z[3].text,'Course Name':z[1].text,'Mid Sem':z[2].text,'Course Title':z[0].text}
    lst.append(dic)

jso = json.dumps(lst)
print jso

f=open("abhi2.txt","w")
f.write(jso)
f.close()









