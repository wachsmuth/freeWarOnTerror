import os


def replaceLine(searchString):
    try: 
        with open("cards/" + searchString + ".java", "r") as f:
            for line in f:
                if "super(" in line:
                    returnLine = line.strip()
                    #Strip
                    returnLine = returnLine.replace(";", ",")
                    returnLine = returnLine[5:]
                    #Remove id
                    returnLine = returnLine.replace(", " + searchString, "")
                    print("found it")
                    return returnLine
            return None
    except FileNotFoundError:
        print("Not found, skipping")
        return None

with open("helpers/CardLookup.java", "r") as f:
    with open("helpers/CardLookupModified.java", "w") as output:
        startList = False
        endList = False
        for line in f:
            if "public enum CardLookup {" in line:
                startList = True
            if ";" in line and startList:
                endList = True
            if startList is True and endList is False and "//" not in line:
                #Format
                newString = line.split("(")[0]
                newString = newString.replace(",", "")
                newString = newString.strip()
                print(newString)
                replaceString = replaceLine(newString)
                if replaceString is not None:
                    print(replaceString)
                    output.write(newString + replaceString + os.linesep)
                    continue
            output.write(line)
