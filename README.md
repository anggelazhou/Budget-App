# My Personal Project

## Budgeting Program

Project Inspiration
>My dad works in the computer science industry and has been talking about making an “invoice application” for years. He 
>never got around to actually making it (as he is extremely busy with work), but the idea stuck with me. Since I first
>heard about this project, I’ve been thinking about how I can use the skills I’ve learnt in this course to make a 
>program with a similar idea. 
>
>As a fresh university student, I am still learning how to be independent. Budgeting my money is something I struggled 
>greatly with in first year. I believe that by designing this program, I will not only get hands-on practice with the 
>authentic software construction process, but I will also be creating something that is useful in my daily life. 

<br>

App Info
>This program aims to help individuals better manage their money. It is suitable for teens and adults looking to track 
>their expenses and plan their budget.
>
>Essentially, this program takes a budget and expenses, and provides a balance. Users are able to create categories 
>(such as groceries, gas, etc.), and are given a general overview of the balances left for each one. When a category is 
>selected, users are able to view previous expenses within the category, as well as will be given the option to add 
>another expense.

<br>

User Stories
> - As a user, I want to be able to add and remove a category
> - As a user, I want to be able to add a budget (for a category)
> - As a user, I want to be able to reset the balance/clear previous expenses
> - As a user, I want to be able to modify a budget
> - As a user, I want to be able to have a general view of my categories and their balances
> - As a user, I want to be able to select a category and view the previous expenses in detail
> - As a user, I want to be able to add an expense (includes expense title and value, listed under a category)
> - As a user, I want to be able to save my budget system to file
> - As a user, I want to be able to be able to load my budget system from file

<br>

Phase 4: Task 2
> - Implemented option 2: "Include a type hierarchy in your code..."
> - Abstract class: ```AddPopUpGUI```
> - Subclasses: ```AddCategoryPopUpGUI, AddExpensePopUpGUI```
> - Overridden methods: 
``` getPopUpType(), getLabel1Text(), getLabel2Text(), addPopUpTypeListener()```

<br>

Phase 4: Task 3
>- To make the ```SelectCategoryPopUpGUI``` more cohesive, I would refactor all methods related to the expenses into a 
>new class called ExpensePanel. This means the ```SelectCategoryPopUpGUI``` class would contain only the rendering for 
>the category's summary, as well as the ExpensePanel class. This reinforces the SRP.
>- Overall, I think it is ok.
