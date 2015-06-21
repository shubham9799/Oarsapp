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

lis1=["Monday","Tuesday","Wednesday","Thursday","Friday"]
lis2=["8.00-9.00","8.30-9.00","9.00-10.00","9.30-10.00","10.00-11.00","10.30-11.00","11.00-12.00","11.30-12.00","12.00-01.00","12.30-1.00"]
lis3=[]
lis4=["2.00-3.00","2.30-3.00","3.00-4.00","3.30-4.00","4.00-5.00","4.30-5.00","5.00-6.00","5.30-6.00","6.00-7.00","6.30-7.00"]
x=soup.find_all("table",{"width":"820"})
y=x[0].find_all("tr")
for i in range(1,len(y)):
    z=y[i].find_all("td")
    for j in range(1,len(z)):
        if j%2!=0:
            p=z[j].find_all("font",{"size":"1"})
            if p!=[]:
                q=p[0].find_all("a")
                for k in range(0,len(q)):
                        dict={"day":lis1[i-1],"time":lis2[j-1],"course":q[k].text}
                        lis3.append(dict)

X=soup.find_all("table",{"width":"750"})
Y=X[0].find_all("tr")
for i in range(1,len(Y)):
    Z=Y[i].find_all("td")
    for j in range(1,len(Z)):
        if j%2!=0:
            P=Z[j].find_all("font",{"size":"1"})
            if P!=[]:
                Q=P[0].find_all("a") 
                for k in range(0,len(Q)):
                            dict={"day":lis1[i-1],"time":lis4[j-1],"course":Q[k].text}
                            lis3.append(dict)

                           
jso=json.dumps(lis3)
f=open("AK.txt","w")
f.write(jso)
f.close
print jso





