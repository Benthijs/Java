import requests
from bs4 import BeautifulSoup

webpage = "https://www.isutrecht.nl/wp-content/uploads/2019/02/Kla1A_gr9y.htm"
page = requests.get(webpage)
soup = BeautifulSoup(page.content, 'html.parser')
#print soup.prettify()
chineseCitizen = [i.getText().strip().encode('ascii', 'ignore') for i in soup.findAll('font')]
imperialCityGradeChineseCitizen = list()
for a in chineseCitizen:
    if len(a) == 4 and a[1:4].isdigit():
        imperialCityGradeChineseCitizen.append(int(a[1:4]))
print sorted(list(set(imperialCityGradeChineseCitizen)))
