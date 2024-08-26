# Assignment 5
## Software Design
1. When the app is started, the user is presented with the main menu, which allows the
user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison
settings, or (4) compare job offers (disabled if no job offers were entered yet1).
    - The above implementation is realised by implementing USER with attributes to add a new job, edit or add current job, update comparison settings and job comparison function. And, since a single user can have multiple jobs it's 1-* association, similarly, user can perform multiple weight updates and comparisons and hence it follows the same association

2. When choosing to enter current job details, a user will:
a. Be shown a user interface to enter (if it is the first time) or edit all the details of
their current job, which consist of:
i. Title
ii. Company
iii. Location (entered as city and state)
iv. Cost of living in the location (expressed as an index)
v. Yearly salary
vi. Yearly bonus
vii. Number of stock option shares offered
viii. Home Buying Program fund (one-time dollar amount up to 15% of Yearly
Salary)
ix. Personal Choice Holidays (A single overall number of days from 0 to 20)
x. Monthly Internet Stipend ($0 to $75 inclusive)
b. Be able to either save the job details or cancel and exit without saving, returning
in both cases to the main menu.
    - The above use case is met by implementing Job class which has attribute to add current job, or new job offer and edit current job. The current Job use case is handled by defining a boolean which is set when current job is added and edit happens for the same. Details like handling save and cancel use cases would be handled in GUI implementation

3. When choosing to enter job offers, a user will:
a. Be shown a user interface to enter all the details of the offer, which are the same
ones listed above for the current job.
b. Be able to either save the job offer details or cancel.
c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the
offer (if they saved it) with the current job details (if present).
    - The above requirement is met using the same Job class with functionality to add new job which has no edit functionality available. Details like handling save and cancel, enter another offer, compare and return to main menu post saving would be handled in GUI implementation

4. When adjusting the comparison settings, the user can assign integer weights to:
a. Yearly salary
b. Yearly bonus
a. Number of Stock Option Shares Offered
a. Home Buying Program Fund
c. Personal Choice Holidays
d. Monthly Internet Stipend
If no weights are assigned, all factors are considered equal.
    - Comparison settings is implemented by use of Weights which has weight attributes for each corresponding attribute in **Job** Class. Details like having default value set for each parameters will be handled in Software implementation

5. When choosing to compare job offers, a user will:
a. Be shown a list of job offers, displayed as Title and Company, ranked from best
to worst (see below for details), and including the current job (if present), clearly
indicated.
b. Select two jobs to compare and trigger the comparison.
c. Be shown a table comparing the two jobs, displaying, for each job:
i. Title
ii. Company
iii. Location
iv. Yearly salary adjusted for cost of living
v. Yearly bonus adjusted for cost of living
vi. Number of Stock Option Shares Offered
vii. Home Buying Program fund (one-time up to 15% of Yearly Salary)
viii. Personal Choice Holidays (A single overall number of days from 0 to 20)
ix. Monthly Internet Stipend ($0 to $75 inclusive monthly)
d. Be offered to perform another comparison or go back to the main menu.
    - Job offer comparsisons is implemented via Comparison which calculates the rank of the job by use of Index and Weight and displays the required info for Jobs being compared. Display and layout functionalities will be handled in GUI implementation

6. When ranking jobs, a job’s score is computed as the weighted average of:
AYS + AYB + (CSO/3) + HBP + (PCH * AYS / 260) + (MIS*12)
where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
CSO = Company shares offered (assuming a 3-year vesting schedule and a
price-per-share of $1)
HBP = Home Buying Program
PCH = Personal Choice Holidays
MIS= Monthly Internet Stipend
For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for
Internet Stipend, and 1 for all other factors, the score would be computed as:
2/9 * AYS + 2/9 * AYB + 1/9 * (CSO/3) + 1/9 * HBP + 1/9 * (PCH * AYS / 260) + 2/9 * (MIS*12)
    - The above use case is implemented by calcRank() function defined in Comparison's scope which reads from Index, Job and Weight to perform the mentioned computation and generate rank for each Job added

7. The user interface must be intuitive and responsive.
    - The above use case is not handled in the design since this will be completely implemented in the GUI development

8. For simplicity, you may assume there is a single system running the app (no
communication or saving between devices is necessary).
    - Taking the above point into consideration the UML showcases no such implementation and will be taken care of in actual application implementation
