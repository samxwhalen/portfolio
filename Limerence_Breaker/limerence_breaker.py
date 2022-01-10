love_object = []
breakers = []

start = input("Would you like to add a positive or negative trait? (p / n) (enter 0 to exit): ").strip().lower()
end_obs = " "


#make this into a while loop

while start !="0":
    while start == "p":
        add = input("What do you adore about them? (enter 0 to exit): ").strip().lower()
        love_object.append(add)
        if add == "0":
            start = "0"
            print("here!")
            end_obs = input("Would you like to break your obession? ").strip().lower()
            break
        
    while start == "n":
        add = input("What do you they do that bothers you? (enter 0 to exit): ").strip().lower()
        breakers.append(add)
        if add == "0":
            start = "0"
            print("over here!")
            end_obs = input("Would you like to break your obession? ").strip().lower()
            break
        

if end_obs == "yes":
    print("yes!")
    






