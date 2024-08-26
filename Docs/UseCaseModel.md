# Use Case Model

<!-- *This is the template for your use case model. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.* -->

**Author**: \<Team030>

## 1 Use Case Diagram

<!-- *This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.* -->

![Use Case Diagram](<../Images/use_case_diagram.png>)
*This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.*

**Actor:** APP user <br>
**Use cases**: 
1. Start Application
2. Enter job offers
3. Enter current job
4. Enter comparison settings
5. Delete job offer
6. Add job offer
7. Enter job offer
8. Save
9. Cancel
10. Edit
11. Adjust the settings
12. Compute weight
13. Calculate Rank



## 2 Use Case Descriptions

<!-- *For each use case in the use case diagram, this section should contain a description, with the following elements:* -->

<!-- - *Requirements: High-level description of what the use case must allow the user to do.* -->
<!-- - *Pre-conditions: Conditions that must be true before the use case is run.* -->
<!-- - *Post-conditions Conditions that must be true once the use case is run.* -->
<!-- - *Scenarios: Sequence of events that characterize the use case. This part may include multiple scenarios, for normal, alternate, and exceptional event sequences. These scenarios may be expressed as a list of steps in natural language or as sequence diagrams.* -->

### Start Application 
- Requirement: The application should present the user with a main menu once started, the user have 4 options: 
1. Enter current job; 
2. Enter job offers; 
3. Enter comparison setting; 

- Pre-conditions: The application is installed on the user's device
- Post-conditions: The three main function are presented to the user
- Scenarios: The user starts the application and is presented with the main menu


### Enter job offers
- Requirement:The user can enter his job offers page and scan all the job offers table.
- Pre-conditions: The user selects the option to enter jobs offers in the main menu.
- Post-conditions: all the job offers which user added will be showed as a table. There are three options user can choose:
1. Enter job offer; 
2. Add job offer; 
3. Delete job offer; 
- Scenarios: 
1. The user enter the job offers page.
2. The system displays a list of available job offers.
3. User can scan all the job offers by sliding the page up and down

### Delete job offer
- Requirement:The user can choose and delete existed job offers.
- Pre-conditions: 
1. The user has already entered the job offers page.
2. The job offer to be deleted must exist in the system.
- Post-conditions: 
1. The job offer has been permanently deleted from the system.
2. The job offers page has been updated to reflect changes in deletion
- Scenarios: 
1. The user select the delete offer option; 
2. The user select job offer which he want to delete; 
3. The job offer has been permanently deleted from the system.
4. The system deletes the corresponding job offer record from the database.
5. The job offers page has been updated to reflect changes in deletion

### Add job offer
- Requirement:The user can add new job offer.
- Pre-conditions: 
1. The user has already entered the job offers page.
- Post-conditions: 
1. The new job offer has been added in the system.
2. The job offers page has been updated to reflect changes in deletion
- Scenarios: 
1. The user opens the job offers page and selects the option to add a new job offer.
2. The user enters the details of the new job offer, such as job title, company name, salary range, etc.
3. The user submits the new job offer details.
4. The system validates and saves the new job offer information to the database.
5. The job offers page is updated to reflect the added changes.

### Enter job offer
- Requirement:The user opens a specific job offer page in the system and views the detailed content of the job offer.
- Pre-conditions: 
1. The user has already entered the job offers page.
2. The job offers database has been established.
- Post-conditions: 
The user is able to view the detailed information of the selected job offer. 
There are three options user can choose:
1. Save; 
2. Cancel; 
3. Edit; 
- Scenarios: 
1. The user navigates to the job offers page and browses the available job offer list.
2. The user selects the Enter job offer option.
3. The user selects the specific job offer to view, and clicks on the offer.
4. The system redirects to the job offer detail page, displaying the detailed content of the job offer and operations buttons.

### Enter current job offer
- Requirement:The user opens current job page in the system and views the detailed content of the current job.
- Pre-conditions: 
1. The user selects the option to enter current job in the main menu.
- Post-conditions: The user is able to view the detailed information of the selected job offer. 
There are three options user can choose:
1. Save; 
2. Cancel; 
3. Edit; 
- Scenarios: 
1. The user starts the application and selects the enter current job offer option.
2. The detailed content of the current job will be showed.
3. If the user never edit the current job, the current job page will only has three function buttons because the current job details are None.

### Save
- Requirement:After editing the details of a job offer or current job, the user has the option to save the changes, which will update the database with the modified information.
- Pre-conditions: 
1. The user has already edited the job offer or current job details.
2. The user is presented with the option to save the changes.
- Post-conditions: 
1. The edited job offer or current job details are saved to the database.
2. The database is updated with the modified information. 
- Scenarios: 
1. After editing the job offer details, the user selects the "Save" option.
2. The system updates the database with the modified details.
3. The job offer or current job page reflects the updated information.

### Cancel
- Requirement:After editing the details of a job offer or current job, the user has the option to cancel the changes, reverting back to the original information.
- Pre-conditions: 
1. The user has already edited the job offer or current job details.
2. The user is presented with the option to cancel the changes.
- Post-conditions: 
1. The edited job offer details are not saved to the database.
2. The page reverts back to display the original job offer or current job information.
- Scenarios: 
1. After editing the job offer details, the user selects the "Cancel" option.
2. The system reverts the job offer details back to their original state.
3. The page reverts back to display the original job offer or current job information.

### Edit
- Requirement:The user has the option to edit the details of a job offer when viewing the specific job offer page or the current job details.
- Pre-conditions: 
1. The user has already entered the specific job offer page or current job page.
2. The user has selected the option to edit.
- Post-conditions: 
The user's edits are made and showed in the page but are not saved to the database.
- Scenarios: 
1. The user navigates to the specific job offer page or the current job details page.
2. The user selects the "Edit" option for the job offer details.
3. The user is able to modify the job offer details, such as job title, company name, salary range, etc.


### Adjust Comparison Settings
- **Requirement**: Allow the user to assign integer weights to various job attributes for comparison purposes.
- **Pre-conditions**: The user selects the option to adjust comparison settings from the main menu.
- **Post-conditions**: The user's preferred weights for comparison are saved.
- **Scenarios**: 
1. Normal Flow: The user assigns weights to each job attribute and saves their preferences, 

### Compare Job Offers:
- **Requirement**: Present the user with a list of ranked job offers, allow selection for comparison, and display a detailed comparison of the selected jobs.
- **Pre-conditions**: At least one job offer has been entered  and the user selects the option to compare job offers from the main menu.
- **Post-conditions**: The user has viewed a comparison of two job offers and can choose to perform another comparison or return to the main menu.
- **Scenarios**: 
1. Normal Flow: The user selects two job offers from the list, and the APP displays a detailed comparison, after which the user can compare other offers or return to the main menu.
2. Alternate flow: If no job offers are entered, the compare option is disabled, and the user is prompted to enter job offers first.

