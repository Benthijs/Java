import requests
from bs4 import BeautifulSoup
from itertools import permutations
pages = ['6y','6m','6p','7y','7m','8y','8m','9y','9m','10y','10m','11','12']
imperialCityGradeChineseCitizen = list()
file = open('inUse.txt', 'w')
for i in pages:
    webpage = "https://www.isutrecht.nl/wp-content/uploads/2019/02/Kla1A_gr" + i + ".htm"
    page = requests.get(webpage)
    soup = BeautifulSoup(page.content, 'html.parser')
    #print soup.prettify()
    representativeChineseCitizen = [i.getText().strip().encode('ascii', 'ignore') for i in soup.findAll('font')]
    chineseCitizen = [i.getText().strip().encode('ascii', 'ignore') for i in soup.select('TD')]
    chineseCitizen = chineseCitizen[chineseCitizen.index('6'):chineseCitizen.index('7')]
    for a in representativeChineseCitizen:
        if len(a) == 4 and a[1:4].isdigit() and a[0] != 'e':
            imperialCityGradeChineseCitizen.append(int(a[1:4]))
q = sorted(list(set(imperialCityGradeChineseCitizen)))
print q
p = list(permutations(q, 5))
f = open('schedules.txt', 'w')
for i in p:
    f.write(i + '\n')
'''day = ['monday','tuesday','wednesday','thursday','friday']
for a in range(5):
    file.write(day[a] + ':\n')
    for i in q:
        file.write(str(i) + '\n')'''
file.close()
