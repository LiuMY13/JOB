# DESIGN DESCRIPTION

## 1. When the app is started, the user is presented with the main menu, which allows the user to 
<font color=Blue>
To realize this requirement, I added a main_menu class, and a user class. In practice, each system requires a user class. The user class contains attributes such as user_id and username, which are not included in this requirement. I will use user_info instead. There is a start() method in the user class. To my perspective, there is an association relationship between the user class and main_menu class, and the user enters main_menu through the start method
</font>

### (1) enter or edit current job details, 
### (2) enter job offers, 
### (3) adjust the comparison settings, or 
### (4) compare job offers (disabled if no job offers were entered yet1).
<font color=Blue>
I will realize some of the above four requirements in main_menu.class. In main_menu class, I designed a total of three methods. They are respectively
1. +Enter_joboffered ()
2. +Enter_current_job()
3. +Enter_comparison_settings()
    
For these three methods, I designed three classes. They are respectively job_offers class, comparison class and current job class.
The relationship between these three classes and main_menu class is use relationship, also known as association. For requirements such as editting job details and adjustment of the comparison settings. We will implement them in these three classes.The purpose is to achieve better encapsulation, so that the code has better maintainability.
</font>


## 2.When choosing to enter current job details, a user will:
### a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
#### i. Title
#### ii. Company
#### iii. Location (entered as city and state)
#### iv. Cost of living in the location (expressed as an index)
#### v. Yearly salary
#### vi. Yearly bonus
#### vii. Number of stock option shares offered
#### viii. Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary)
#### ix. Personal Choice Holidays (A single overall number of days from 0 to 20)
#### x. Monthly Internet Stipend ($0 to $75 inclusive)
### b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
## 3. When choosing to enter job offers, a user will:
### a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
### b. Be able to either save the job offer details or cancel.
### c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

<font color=Blue>

1. Based on my experience, job offer and current job are the same. They all contain job details, as well as edit, save, cancel and exit operations. So here I use inheritance to realize. Firstly, design a job_detail class that includes titles, companies, and more. And based on my experience, set specific data types as attributes.
   - Title: String
   - Company: String
   - Location: String
   - Cost_of_living_in_the_location: Int
   - Yeartly_salary: double
   - Yearly_bonus: double
   - Home_Buying_Program_fund: double
   - Personal_Choice_Holidays: int
   - Monthly_Internet_Stipend: double

   Then, I will add four methods to the detail class.
   - +Save(): void
   - +Cancel(): void
   - +Enter(): details
   - +Edit(): void
    
   Save() is used to save edition.
   Edit() is used to modify attribute information. In the implementation process, the method should include the passed in parameters. In the design phase, we will ignore it first.
   Cancel() does not save information and exits directly.
   Enter() is used to retrieve information and return the attributes in the job_details.


2. To distinguish between offer and current job, I added two classes, job_offer and current_job, to inherit the job_details class
3. Designed a job_offers class, where a job_offers class contains multiple job_offer classes. So the attribute of job_offers class is an array composed of multiple job_offer. So the relationship between the job_offer class and the job_offers class is aggregation.
4. Although it is not included in the requirements, we have added operation add(job_offer) in the job_offers class to add a new job offer. Added operation delete(job_offer) for deletion. Enter_job_offer(job_offer), used to open a specific job_offer. Exit() is used to return main_menu class. Compare (job_offer, job_offer) is used for comparison, and the specific implementation will be discussed later.
   
</font>

## 4. When adjusting the comparison settings, the user can assign integer weights to:
### a. Yearly salary
### b. Yearly bonus
### a. Number of Stock Option Shares Offered
### a. Home Buying Program Fund
### c. Personal Choice Holidays
### d. Monthly Internet Stipend 
If no weights are assigned, all factors are considered equal.
## 5. When choosing to compare job offers, a user will:
### a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
### b. Select two jobs to compare and trigger the comparison.
### c. Be shown a table comparing the two jobs, displaying, for each job:
#### i. Title
#### ii. Company
#### iii. Location
#### iv. Yearly salary adjusted for cost of living
#### v. Yearly bonus adjusted for cost of living
#### vi. Number of Stock Option Shares Offered
#### vii. Home Buying Program fund (one-time up to 15% of Yearly Salary)
#### viii. Personal Choice Holidays (A single overall number of days from 0 to 20)
#### ix. Monthly Internet Stipend ($0 to $75 inclusive monthly)
### d. Be offered to perform another comparison or go back to the main menu.
## 6. When ranking jobs, a job’s score is computed as the weighted average of:


<font color=Blue>
To implement the compare method. We have designed a comparation_settings class. 

1. Its properties include
   - -Yearly_salary_weight: double
   - -Yeraly_bonus_weight: double
   - -Number of StockOptions SharesOffer_weight: double
   - -HomeeBuying-Program-Fund_weight: double
   - -Personal.Choice_Holidays_weight: double
   - -Monthly Internet_stipend_weight: double
  
2. Its operations include
   + adjust_the_comparison_settings(): void
   + compute_weight(job_details): double
     
     Adjust_the_comparison_settings() is used to modify weights.  In the implementation process, the method should include the passed in parameters. In the design phase, we will ignore it first.
     Compute_weight(job_details) is used to calculate the score of job offer or current job, used for subsequent sorting. Hence, we added a atrribute called "score" to job_offer class and current_job class. And I used "/" as a prefix for derived attributes.
     Class job_offers used comparation_settings class to realize the operation "compare()". So, the relation between job_offers class, job_offer class and current_job class is association.
3. I did not achieve requirement 6 and requirement 5 c. Because Requirement 6 is the specific algorithm for score, and Requirement 5c is the specific output format of ranking requirement. These requirements do not directly affect the design, but rather the algorithms that need to be noted during the implementation process.


<front>