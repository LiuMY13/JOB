## **CS6300 Assignment 5 Software Design**
## <ins>**Design Description Document**


With reference to the instructions of Assignment 5: Software Design, I will explain whether each of the given requirements will directly affect the design and further showcase how my design aims to realise each of the identified requirements.


#### <ins>*Requirement 1*:
When the app is started, the user is presented with the main menu, which allows the user to <br>
1)  enter or edit current job details, <br>
2)  enter job offers, <br>
3)  adjust the comparison settings, or <br>
4)  compare job offers (disabled if no job offers were entered yet). <br>

#### <ins>*Explanation for Requirement 1*:
This requirement states the 4 different functions that the main menu page should allow the user to perform. This will not be represented in my design, as these will be handled entirely within the GUI implementation. This is because the main menu page would have individual buttons or hyperlinks that will lead the user to individual distinct interfaces to perform the desired action. <br>
This requirement also mentions a lot about the term 'job', which hints that there should be a *'Job'* class.

#### <ins>*Requirement 2*:
When choosing to enter current job details, a user will:<br>
a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
 1) Title
 2) Company
 3) Location (entered as city and state)
 4) Cost of living in the location (expressed as an index)
 5) Yearly salary
 6) Yearly bonus 
 7) Number of stock option shares offered
 8) Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary)
 9) Personal Choice Holidays (A single overall number of days from 0 to 20)
 10) Monthly Internet Stipend ($0 to $75 inclusive) <br>

b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

#### <ins>*Explanation for Requirement 2*:

I will first start by creating the *'Job'* class as mentioned previously. This class will contain the attributes as listed in this requirement. Next, it is important to note that each entry for this class is unique, so there must be a unique identifier for each entry. <br><br> Additionally, it is stated that the location should be entered as a city and state. I decided to create a *'Location'* class for this so as to minimise unneccessary repetition in the database later on. I believe it will be much cleaner for the user to select options from a list such that we firstly do not need to account for uppercase or lowercase and secondly this can facilitate as a filter later on.  <br><br>
Though this requirement only talks mainly about the function to enter current job details, it is important to note that the next requirement says that the details of job offers are the same as the ones listed for current job. <br>
This implies that there can be a *'Job'* class and then I can create separate classes for *'Current'* and *'Offer'* where the common attributes will be listed in the *'Job'* class. <br><br>

More specifically for the *'Current'* class, I decided to include a *createdAt* attribute such that if there is no current job, then this will be null and can therefore allow to prompt the user to enter details on his first time. If the *createdAt* attribute has a date value already, then this will prompt the user to edit the details instead. This brings to the next attribute of *updatedAt* that can help to track when was the entry last updated. There is also the operation *updateJobDetails* that will be executed when the user wants to save job details.

#### <ins>*Requirement 3*:
When choosing to enter job offers, a user will: <br>
a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.<br>
b. Be able to either save the job offer details or cancel.<br>
c. Be able to
 1) enter another offer,
 2) return to the main menu, or
 3) compare the offer (if they saved it) with the current job details (if present).

#### <ins>*Explanation for Requirement 3*:

I discussed in the previous requirement that there will be an *'Offer'* class, from which there will be additional attributes of *createdAt* and *validUntil*. There is no indication that the user can update the job offer details, so I only suggest for a *createdAt* attribute. Also, it would make more sense for a jobseeker to want to perform comparisons on recent job offers versus those which were like a decade ago, though I would not rule out the edge possibility of a user doing so. <br><br>
Next, I will have the operation of *comparisonAgainstCurrent* as that is stated as a functionality that the user could perform a comparison of the offer upon saving it with the current job details.


#### <ins>*Requirement 4*:
When adjusting the comparison settings, the user can assign integer weights to:<br>
a. Yearly salary<br>
b. Yearly bonus<br>
c. Number of Stock Option Shares Offered<br>
d. Home Buying Program Fund<br>
e. Personal Choice Holidays<br>
f. Monthly Internet Stipend<br>
If no weights are assigned, all factors are considered equal.

#### <ins>*Explanation for Requirement 4*:

This requirement is straightforward, where I will be providing a *'ComparisonSettings'* class that will have each of the points listed as attributes. It is also a requirement that they are integer values. <br><br>

However, it is also stated to account for the case where no weights are assigned. Therefore, I would suggest having the operation *defaultSettings* where all the attributes will be assigned the same value.

#### <ins>*Requirement 5*:
When choosing to compare job offers, a user will:<br>
a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.<br>
b. Select two jobs to compare and trigger the comparison.<br>
c. Be shown a table comparing the two jobs, displaying, for each job:<br>
 i. Title<br>
 ii. Company<br>
 iii. Location<br>
 iv. Yearly salary adjusted for cost of living<br>
 v. Yearly bonus adjusted for cost of living <br>
 vi. Number of Stock Option Shares Offered<br>
 vii. Home Buying Program fund (one-time up to 15% of Yearly Salary)<br>
 viii. Personal Choice Holidays (A single overall number of days from 0 to 20) <br>
 ix. Monthly Internet Stipend ($0 to $75 inclusive monthly)<br>
d. Be offered to perform another comparison or go back to the main menu.

#### <ins>*Explanation for Requirement 5*:
To handle this requirement, I will be providing a *'Comparison'* class, where the operation *displayJobList* will display the list of offers. This class will have 2 attributes to indicate the identifier for each of the 2 selected jobs that will be taken for comparison. As for the comparison, the details will be mentioned under the next requirement on computing the scores.

#### <ins>*Requirement 6*:
When ranking jobs, a job’s score is computed as the weighted average of:  AYS + AYB + (CSO/3) + HBP + (PCH * AYS / 260) + (MIS*12)  where:
- AYS = yearly salary adjusted for cost of living
- AYB = yearly bonus adjusted for cost of living
- CSO = Company shares offered (assuming a 3-year vesting schedule and a price-per-share of $1)
- HBP = Home Buying Program PCH = Personal Choice Holidays 
- MIS= Monthly Internet Stipend 

For example, if the weights are 2 for the yearly salary, 2 for the yearly bonus, 2 for Internet Stipend, and 1 for all other factors, the score would be computed as:
2/9 * AYS + 2/9 * AYB + 1/9 * (CSO/3) + 1/9 * HBP + 1/9 * (PCH * AYS / 260) + 2/9 * (MIS*12)

#### <ins>*Explanation for Requirement 6*:

Here, this is the main gist on which the comparison functionality hinges. I will first start with the *'ComputeScore'* class where the main sttribute would be the *score* and the operation will be *computeScore*. <br><br>

This class will have relationships with the *'Job'* class, where the values of the different job attributes will be taken into account, then subsequently, based on the relationship with the *'ComparisonSettings'* class, we can retrieve the weights and then perform the computation to generate the score. <br>
This score will then be taken into account for the previous requirement where the jobs have to be ranked in a table  and it is also important when comparing the 2 jobs.


#### <ins>*Requirement 7*:
The user interface must be intuitive and responsive.

#### <ins>*Explanation for Requirement 7*:
As this is mainly a UI issue, this will not be handled by my design here. Nonetheless, this requirement is still important to meet the user experience and can be visualised later on using wireframes, which isnt the focus of this assignment.

#### <ins>*Requirement 8*:
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

#### <ins>*Explanation for Requirement 8*:
Based on this simplicity assumption, I will then further postulate that my good friend George will be the sole user of this application given that it is only running on his device. This implies I do not need to account for different users.