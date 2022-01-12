import random

love_object = []
breakers = []

start = " "
end_obs = " "

print("Welcome to the Limerence Breaker! I'm here to help you get over someone.")
print("First, tell me about your love interest..")
start = input("Would you like to add a positive or negative trait? (p / n) (enter 0 to exit): ").strip().lower()

while start !="0":
    while start == "p":
        add = input("What do you adore about them? (enter 1 to exit): ").strip().lower()
        if add == "1":
            if len(breakers) == 0:
                start = "n"
                break
            else:
                start = "0"
        love_object.append(add)


    while start == "n":
        add = input("What do you they do that bothers you? (enter 1 to exit): ").strip().lower()
        if add == "1":
            if len(love_object) == 0:
                pos = input("Would you like to add a positive trait? (y / n) (enter 0 to exit): ").strip().lower()
                if pos == "y":
                    start = "p"
                else:
                    start = "0"
            else:
                start = "0"
        breakers.append(add)

 
end_obs = input("Would you like to break your obession? ").strip().lower()

if end_obs == "yes":
    while end_obs == "yes":
        contact = input("Are you thinking about calling/texting them?").strip().lower()
        if contact == "yes":
            ran_num = random.randint(0, len(breakers) - 1)
            reason = breakers[ran_num]
            print("Well dont! Because you said that they " + "'" + reason + "'. And you don't really want to be with someone who would act that way, do you?")
        if contact == "no":
            if len(love_object) > 0:
                love_ran = random.randint(0, len(love_object) - 1)
                reason = love_object[love_ran]
                print("GOOD! You say '" + reason + "' but that doesn't mean that they are good for you! Why don't you do something to take your mind off of them?")
                break
            else:
                print("GOOD! I'll be here if you need another reason to not reach out to them. You got this!")
                break


if end_obs == "no":
    print("Okay then...")
print("Come on back whenever you need help letting go!")
    






