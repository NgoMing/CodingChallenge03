# 1-1800-FLOWERS

Many companies like to list their phone numbers using the letters printed on most
telephones. This makes the number easier to remember for customers. An example may
be 1-800-FLOWERS.

Requirements:
* Show a user possible matches for a list of provided phone numbers.
* Should be a command line app, reads from files specified as command line args or STDIN. 
  A single phone number per line in the file.
* Output all possible word replacements from a dictionary for each phone number.
* Should try to replace all digit or only one digit is allowed to be left.
* Skip the phone number which is impossible to replace.
* Allow users to set a dictionary with the -d command line option, if not, default dictionary should be used.
* All punctuation and whitespace should be ignored.
* Non case sensitive, output should be capital letters, separated with a single dash(-), one possible word per line.

## Instructions
#### Create rules which identify the conversion from a digit to letters.
* Users can create a new one: `-rules [digit] [letter1] [letter2] ...`
* But they also can use the default rules: `-rules default`
###### Default rules
```
* 2 => A, B, C
* 3 => D, E, F
* 4 => G, H, I
* 5 => J, K, L
* 6 => M, N, O
* 7 => P, Q, R, S
* 8 => T, U, V
* 9 => W, X, Y, Z
```
#### Identify dictionary file - which will store all the possible words.
* Users can choose their own file by move the file to Resource folder: `-dict [dictionaryFileName.txt]`
* Or they can also use the default file: `-dict default`
#### Convert single phone number
* Users can convert single phone number: `-exe [phone_number]`
* If rules is not available, users can set rules at that time by using given above commands or using default rules.
* If dictionary is not available, users can set dictionary at that time by using given commands 
  or using default dictionary.
#### Convert multiple phone numbers ( in a file .txt)
* Users can convert multiple phone numbers via a text file located in Resource folder: `-file [phoneNumberFileName.txt]`
* If rules is not available, users can set rules at that time by using given above commands or using default rules.
* If dictionary is not available, users can set dictionary at that time by using given commands 
  or using default dictionary.
#### Other current useful commands
##### `-rules view`
Show the current rules
##### `-dict view`
Show the current dictionary
##### `-exe view`
Show the result after converting phone number to words (separate by "-")

#### Other future useful commands
##### `-rules [rulesFileName.txt]`
Set rules in identified file.
##### `-rules verify`
Show problems with the current rules such as rules are not completed or some values are duplicated:
##### `-rules save [rulesFileName.txt]`
Save users' rules in Resource folder for next using time.
##### `-rules list`
Show the list of exist rules in Resource folder.

##### `-dict list`
Show the list of exist dictionaries in Resource folder.

## Solution Design

### Using command pattern to manipulate user commands
##### Invoker
* UserCommand
##### Receiver
* Rule commands
* Dictionary commands
* Execution commands
##### Concrete Commands
Including all the mentioned commands.
### Using singleton pattern for map instance
* Prevent undesired editing outside class and
* Guarantee that the maps are unique through the related commands
* Including maps for: digit to letter rules, word to number, and phone number to words (separated by "-")

### Utilities
* Control signal: "[order_of_command]>>>" support for testing (assertThat method).
* FileUtil: support reading all lines or each line of file in resource folder.
* StringAsker: support testing input and output of console.

## Installation and usage

### System requirements

* JDK 8

### Build and Test

Windows:
```
./gradlew clean build
```

### Run

Windows
```
java -jar build/libs/challenge03-1.0-SNAPSHOT.jar
```
## Test cases
#### Cases related to `-rules` commands
1. Creating default rules
```
* -rules default
* -rules view
* expected: default_rules.txt
```

2. Creating a new rule
```
* -rules [digit] [letter1] [letter2] ...
* -rules view
* using JUnitParameter test compile
```

3. Viewing rules without creating rules
```
* -rules view
* expected error_view_without_rules.txt 
(rules have not been set up yet and show rules command list)
```

4. Wrong rules command
```
* -rules abc
* expected error_rules_command.txt 
(rules command not found and show rules command list)
```

#### Cases related to `-dict` commands
5. Creating default dictionary with rules
```
* -rules default
* -dict default (using default_dictionary.txt)
* -dict view
* expected default_converted_dictionary.txt
```

6. Creating default dictionary without rules
```
* -dict default (using default_dictionary.txt)
* -dict view
* expected error_view_without_rules.txt 
(`rules have not been set up yet and show rules command list`)
```

7. Viewing dictionary with rules but dictionary
```
* -rules default
* -dict view
* expected error_view_without_dictionary.txt 
(dictionary has not been ready yet and show dictionary command list)
```

8. Viewing dictionary without rules and dictionary
```
* -dict view
* expected error_view_without_rules.txt 
(rules have not been set up yet and show rules command list)
```

#### Cases related to `-exe` commands
9. Convert single phone number with rules and dictionary
```
* -rules default
* -dict default
* -exe 225563
* expected CALL-ME, BALL-ME
```

10. Convert single phone number with rules but dictionary
```
* -rules default
* -exe 225563
* expected error_view_without_dictionary.txt 
(dictionary has not been ready yet and show dictionary command list)
```

11. Convert single phone number without rules and dictionary
```
* -exe 225563
* expected error_view_without_rules.txt 
(rules have not been set up yet and show rules command list)
```

12. Convert multiple phone numbers with rules and dictionary
```
* -rules default
* -dict default
* -exe phone_number_default.txt
* expected
```

13. Convert multiple phone numbers with rules but dictionary
```
* -rules default
* -exe phone_number_default.txt
* expected error_view_without_dictionary.txt 
(dictionary has not been ready yet and show dictionary command list)
```

14. Convert multiple phone numbers without rules and dictionary
```
* -exe phone_number_default.txt
* expected error_view_without_rules.txt 
(rules have not been set up yet and show rules command list)
```

15. Viewing phone number without rules or dictionary or both
```
* -exe view
* expected "There is not any result"
```
