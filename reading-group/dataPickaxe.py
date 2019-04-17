# Used initially to get all classrooms
import requests
from bs4 import BeautifulSoup
import itertools


def dataPickaxe():
    pages = ['6y', '6m', '6p', '7y', '7m', '8y', '8m', '9y', '9m', '10y', '10m', '11', '12']
    imperialCityGradeChineseCitizen = list()
    file = open('inUse.txt', 'w')
    # file = open('')
    for i in pages:
        webpage = "https://www.isutrecht.nl/wp-content/uploads/2019/02/Kla1A_gr" + i + ".htm"
        page = requests.get(webpage)
        soup = BeautifulSoup(page.content, 'html.parser')
        # print soup.prettify()
        representativeChineseCitizen = [i.getText().strip().encode(
            'ascii', 'ignore') for i in soup.findAll('font')]
        chineseCitizen = [i.getText().strip().encode('ascii', 'ignore') for i in soup.select('TD')]
        chineseCitizen = chineseCitizen[chineseCitizen.index('6'):chineseCitizen.index('7')]
        for a in representativeChineseCitizen:
            if len(a) == 4 and a[1:4].isdigit() and a[0] != 'e' and int(a[1:4]) != 17:
                imperialCityGradeChineseCitizen.append(int(a[1:4]))
    q = sorted(list(set(imperialCityGradeChineseCitizen)))
    p = list(itertools.permutations(q, 5))  # List of all possible room schedules
    # Add the inUse boolean to each schedule
    inUse = open('FilledinUse.txt', 'r').read()
    inUse = [filter(lambda a: len(a) > 0, i.split('\n')) for i in inUse.split('/')][0:-1]
    print len(inUse)
    print inUse[4]
    library = []
    for i in inUse:
        lib = {}
        for a in i:
            s = a.split(' ')
            lib[s[0]] = s[1]
        library.append(dict(lib))
    f = open('schedules.txt', 'w')
    schedule = list()
    for i in range(len(p)):
        e = ''
        d = len(p[0])
        for a in range(d):
            e += str([p[i][a], int(library[a][str(p[i][a])])])
            if a < d - 1:
                e += ':'

        f.write(e + '\n')
        schedule.append(e)
        # f.write(str(i) + '\n')
    day = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday']
    for a in range(5):
        file.write(day[a] + ':\n')
        for i in q:
            file.write(str(i) + '\n')
    file.close()
    p = list(itertools.product(*(i for i in inUse)))
    # for i in p:
    #    f.write(str(p) + '\n')
    # f.close()
    return schedule


dataPickaxe()
