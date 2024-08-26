# Assignment 5
## Software Design
### Requirement 1
*When the app is started, the user is presented with the main menu, which allows the
user to <br>
&nbsp; &nbsp; &nbsp; &nbsp; (1) enter or edit current job details <br>
&nbsp; &nbsp; &nbsp; &nbsp; (2) enter job offers <br>
&nbsp; &nbsp; &nbsp; &nbsp; (3) adjust the comparison settings, or <br>
&nbsp; &nbsp; &nbsp; &nbsp; (4) compare job offers (disabled if no job offers were entered yet1).* <br>

    The above implementation is realised by implementing USER class with attributes to enter a job offer, enter current job, enter comparison settings. And, since the user can also edit current job it is handled by the same enter_current_job function.
    
### Requirement 2
*When choosing to enter current job details, a user will: <br>
a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of: <br>
&nbsp; &nbsp; &nbsp; &nbsp; i. Title <br>
&nbsp; &nbsp; &nbsp; &nbsp; ii. Company <br>
&nbsp; &nbsp; &nbsp; &nbsp; iii. Location (entered as city and state) <br>
&nbsp; &nbsp; &nbsp; &nbsp; iv. Cost of living in the location (expressed as an index) <br>
&nbsp; &nbsp; &nbsp; &nbsp; v. Yearly salary <br>
&nbsp; &nbsp; &nbsp; &nbsp; vi. Yearly bonus <br>
&nbsp; &nbsp; &nbsp; &nbsp; vii. Number of stock option shares offered <br>
&nbsp; &nbsp; &nbsp; &nbsp; viii. Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary) <br>
&nbsp; &nbsp; &nbsp; &nbsp; ix. Personal Choice Holidays (A single overall number of days from 0 to 20) <br>
&nbsp; &nbsp; &nbsp; &nbsp; x. Monthly Internet Stipend ($0 to $75 inclusive) <br>
b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.* <br>

    Based on the description, job offer and current job are the same since both contain job details, as well as edit, save, cancel and exit operations. So, we use inheritance to realize the requirement by creating a job_detail class that includes titles, companies, and more. And set below mentioned specific data types as attributes,
    - Title: String
    - Company: String
    - City: String
    - State: String
    - Cost_of_living_in_the_location: Int
    - Yeartly_salary: double
    - Yearly_bonus: double
    - Home_Buying_Program_fund: double
    - Personal_Choice_Holidays: int
    - Monthly_Internet_Stipend: double

    Corresponding methods associated to the detail class.
    - +Save(): void
    - +Cancel(): void
    - +Enter(): details
    - +Edit(): void
    
    Save() is used to save edition.
    Edit() is used to modify attribute information. In the implementation process, the method should include the passed in parameters. In the design phase, we will ignore it first.
    Cancel() does not save information and exits directly.
    Enter() is used to retrieve information and return the attributes in the job_details.

### Requirement 3
 When choosing to enter job offers, a user will: <br>
&nbsp; &nbsp; &nbsp; &nbsp; a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job. <br>
&nbsp; &nbsp; &nbsp; &nbsp; b. Be able to either save the job offer details or cancel. <br>
&nbsp; &nbsp; &nbsp; &nbsp; c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present). <br>

    The above requirement is met using the same job_details class with functionality to add new job which has no edit functionality available.where a job_offers class contains multiple job_offer classes. So the attribute of job_offers class is an array composed of multiple job_offer and the relationship between the job_offer class and the job_offers class is aggregation one-to-many.
    Although it is not included in the requirements, we have added operation add(job_offer) in the job_offers class to add a new job offer. Added operation delete(job_offer) for deletion. Enter_job_offer(job_offer), used to open a specific job_offer. Exit() is used to return main_menu class. Compare (job_offer, job_offer) is used for comparison, and the specific implementation will be discussed later.

### Requirement 4
When adjusting the comparison settings, the user can assign integer weights to: <br>
&nbsp; &nbsp; &nbsp; &nbsp; a. Yearly salary <br>
&nbsp; &nbsp; &nbsp; &nbsp; b. Yearly bonus <br>
&nbsp; &nbsp; &nbsp; &nbsp; a. Number of Stock Option Shares Offered <br>
&nbsp; &nbsp; &nbsp; &nbsp; a. Home Buying Program Fund <br>
&nbsp; &nbsp; &nbsp; &nbsp; c. Personal Choice Holidays <br>
&nbsp; &nbsp; &nbsp; &nbsp; d. Monthly Internet Stipend <br>
If no weights are assigned, all factors are considered equal. <br>

    This requirement is straightforward, where we have provided a *'ComparisonSettings'* class that will have each of the points listed as attributes. It is also a requirement that they are integer values and it includes calcRank function to compute AYS and AYB taking Location index into consideration which is computed by City and State of the job offer. Details like having default value set for each parameters will be handled in Software implementation    
    
### Requirement 5
When choosing to compare job offers, a user will: <br>
&nbsp; &nbsp; a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated. <br>
&nbsp; &nbsp; b. Select two jobs to compare and trigger the comparison. <br>
&nbsp; &nbsp; c. Be shown a table comparing the two jobs, displaying, for each job: <br>
&nbsp; &nbsp; &nbsp; &nbsp; i. Title <br>
&nbsp; &nbsp; &nbsp; &nbsp; ii. Company <br>
&nbsp; &nbsp; &nbsp; &nbsp; iii. Location <br>
&nbsp; &nbsp; &nbsp; &nbsp; iv. Yearly salary adjusted for cost of living <br>
&nbsp; &nbsp; &nbsp; &nbsp; v. Yearly bonus adjusted for cost of living <br>
&nbsp; &nbsp; &nbsp; &nbsp; vi. Number of Stock Option Shares Offered <br>
&nbsp; &nbsp; &nbsp; &nbsp; vii. Home Buying Program fund (one-time up to 15% of Yearly Salary) <br>
&nbsp; &nbsp; &nbsp; &nbsp; viii. Personal Choice Holidays (A single overall number of days from 0 to 20) <br>
&nbsp; &nbsp; &nbsp; &nbsp; ix. Monthly Internet Stipend ($0 to $75 inclusive monthly) <br>
&nbsp; &nbsp; d. Be offered to perform another comparison or go back to the main menu <br>

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

### Requirement 6
When ranking jobs, a job’s score is computed as the weighted average of: <br>
&nbsp; &nbsp; &nbsp; &nbsp; AYS + AYB + (CSO/3) + HBP + (PCH * AYS / 260) + (MIS*12) <br>
where: <br>
&nbsp; &nbsp; AYS = yearly salary adjusted for cost of living <br>
&nbsp; &nbsp; AYB = yearly bonus adjusted for cost of living <br>
&nbsp; &nbsp; CSO = Company shares offered (assuming a 3-year vesting schedule and a price-per-share of $1) <br>
&nbsp; &nbsp; HBP = Home Buying Program <br>
&nbsp; &nbsp; PCH = Personal Choice Holidays <br>
&nbsp; &nbsp; MIS= Monthly Internet Stipend <br>
For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for Internet Stipend, and 1 for all other factors, the score would be computed as: <br>
&nbsp; &nbsp; &nbsp; &nbsp; 2/9 * AYS + 2/9 * AYB + 1/9 * (CSO/3) + 1/9 * HBP + 1/9 * (PCH * AYS / 260) + 2/9 * (MIS*12) <br>

    The above use case is implemented by calcRank function defined in comparison_settings class which reads from Index, Job and Weight to perform the mentioned computation and generate rank for each Job added.
    This score will then be taken into account for the previous requirement where the jobs have to be ranked in a table  and it is also important when comparing the 2 jobs.

### Requirement 7
The user interface must be intuitive and responsive. <br>

    As this is mainly a UI issue, this will not be handled in our design here. Nonetheless, this requirement is still important to meet the user experience and can be visualised later on using wireframes, which isnt the focus of this assignment.

### Requirement 8
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary). <br>

    Given the assumption that there is a single system running the app, this means we do not have to take into account differences between various platforms (eg, iOS or Android) and hence do not need to implement cross-platform logic or account for compatibility across multiple devices. <br>
    Instead we can focus on purely having a ‘User’ class for identity access management to protect data across different users.