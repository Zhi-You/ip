# [User Guide](https://zhi-you.github.io/ip/)
Duke can help you **manage and keep track of your tasks** easily through the use of a Command Line Interface.

*Start planning your daily schedule with Duke!*

## Features 
### Add tasks to your task list
There are three types of tasks that you can add to your own task list:
1. Todo
2. Deadline
3. Event

A **Todo** task should be used to keep track of a task without any timing constraints.

A **Deadline** task can be used to keep track of any task with a specific deadline.

An **Event** task can be used to keep track of a task that will happen on a specfic date and time.

### Show the tasks on your task list
You can view your own list of tasks anytime while using Duke!

### Find tasks using keyword(s)
Search for specific tasks in your task list using keywords that match their descriptions.

### Mark a task as done
Whenever you are done with a task, mark it as done within your task list!

### Delete a task from your task list
You can remove any task that you do not want to track anymore.

### Duke understands dates
Pass in a date in a simple format and Duke will store it for you in a clearer format!

### Your task list is automatically saved
You do not have to worry about losing any progress made on Duke as your task list is automatically saved after every action that affects the list. The saved data can be found in `data/tasks.txt` relative to the location storing the JAR file for Duke. 

### Your task list will be loaded automatically on start up
Of course, all the tasks you have in your task list will always come back to you whenever you start up Duke again!


## Usage

### Starting Duke
Ensure your computer has Java 11 before following the steps below to start up Duke.

1. Place the JAR file in any folder
2. Open a command window in that folder
3. Run the following commands in the same folder as the jar file:
```
chcp 65001
java -Dfile.encoding=UTF-8 -jar ip.jar
```
Expected outcome:
```
(Note: Could not detect any saved data!)
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

______________________________________________________
(Available commands are: 'list', 'todo', 'event',
'deadline', 'done', 'delete', 'find' and 'bye')
(Note: Dates should be given in 'yyyy-mm-dd' format)
______________________________________________________
Hello! I'm Duke
What can I do for you?
______________________________________________________
```
If tasks' done status icons appear as `?` for you instead of `✘` and `✓`, change the font in your terminal to NSimSun.

### `todo` - Adds a Todo task to your task list
Adds a Todo task into your task list based on the task's description.


Format: `todo [task description]`

Example of usage: 
```
todo return book
```

Expected outcome:

```
______________________________________________________
Got it. I've added this task:
  [T][✘] return book
Now you have 1 task in the list.
______________________________________________________
```


### `deadline` - Adds a Deadline task to your task list
Adds a Deadline task into your task list based on the task's description and its deadline.

Any date given in the format of `yyyy-mm-dd` (e.g., `2019-10-15`) will be registered in a nicer format of `MMM dd yyyy` (e.g., `Oct 15 2019`).


Format: `deadline [task description] /by [deadline]`

Example of usage: 
```
deadline finish math assignment /by 2020-10-10
```

Expected outcome:

```
______________________________________________________
Got it. I've added this task:
  [D][✘] finish math assignment  (by: Oct 10 2020)
Now you have 2 tasks in the list.
______________________________________________________
```

### `event` - Adds an Event task to your task list
Adds an Event task into your task list based on the task's description and its event timing.

Any date given in the format of `yyyy-mm-dd` (e.g., `2019-10-15`) will be registered in a nicer format of `MMM dd yyyy` (e.g., `Oct 15 2019`).


Format: `event [task description] /at [event timing]`

Example of usage: 
```
event project meeting /at 2020-11-11 3pm at YIH
```

Expected outcome:

```
______________________________________________________
Got it. I've added this task:
  [E][✘] project meeting  (at: Nov 11 2020 3pm at YIH)
Now you have 3 tasks in the list.
______________________________________________________
```


### `list` - Displays your task list
Prints out an indexed list of tasks that you have along with their done status and task's description.

Example of usage: 
```
list
```

Expected outcome:

```
______________________________________________________
1.[T][✘] return book
2.[D][✘] finish math assignment  (by: Oct 10 2020)
3.[E][✘] project meeting  (at: Nov 11 2020 3pm at YIH)
______________________________________________________

```


### `find` - Finds tasks based on keyword(s)
Prints out an indexed list of tasks with descriptions that contain matching sequence of characters with the keyword(s) you specified.


Format: `find [keywords]`

Example of usage: 
```
find math
```

Expected outcome:

```
______________________________________________________
Here are the matching tasks in your list: 
1.[D][✘] finish math assignment  (by: Oct 10 2020)
______________________________________________________
```


### `done` - Marks a task as done 
Marks the specified task as a completed task and its done status icon will automatically be updated to a tick to reflect this.

You can specify the task using its index in the task list.

Format: `done [task index]`

Example of usage: 
```
done 2
```

Expected outcome:

```
______________________________________________________
Nice! I've marked this task as done: 
  [D][✓] finish math assignment  (by: Oct 10 2020)
______________________________________________________
```

Using `list` now, the following task list will be displayed:

```
______________________________________________________
1.[T][✘] return book
2.[D][✓] finish math assignment  (by: Oct 10 2020)
3.[E][✘] project meeting  (at: Nov 11 2020 3pm at YIH)
______________________________________________________
```


### `delete` - Deletes a task from your task list
Removes a task, that you specify, from the task list.

You can specify the task using its index in the task list.

Format: `delete [task index]`

Example of usage: 
```
delete 2
```

Expected outcome:

```
______________________________________________________
Noted. I've removed this task: 
  [D][✓] finish math assignment  (by: Oct 10 2020)
Now you have 30 tasks in the list.
______________________________________________________
```

Using `list` now, the following task list will be displayed:

```
______________________________________________________
1.[T][✘] return book
2.[E][✘] project meeting  (at: Nov 11 2020 3pm at YIH)
______________________________________________________
```

### `bye` - Exits Duke
Terminates the Duke program when you are done using Duke.

Example of usage: 
```
bye
```

Expected outcome:

```
______________________________________________________
Bye. Hope to see you again soon!
______________________________________________________
```
