# Job Offer Comparison App Design Description

## Overview
The Job Offer Comparison App assists single user in comparing job offers, considering various factors as discussed in this document. This document outlines the key components, operations and relationships within the app based on the provided requirements. The user interfaces are not directly depicted in the diagram. 

## Class Descriptions

### User Main Menu
- Functions: Displays the main menu options to the user and captures their selection.
- Interactions: Communicates the user's selection to `App Processing` for further action.

### App Processing
- Functions: Serves as the entry point of the application, tieing various pieces together, coordinating between `User Main Menu` and other parts.
- Interactions: Interacts with `User Main Menu`, invokes `Offer Manager` and `Weights`.

### Offer Manager
- Functions: enter new offer, rank offer list and compare offers 
- Interactions: Interacts with `APP Processing`, contains `Offer Details`, invokes `Job Score Calculator`.  

### Weights
- Functions: Stores user-defined weights for different job offer attributes to be used in `Job Score Calculator`.
- Interactions: Accessed by `APP Processing`, interacts with `Job Score Calculator` to provide weights of each factor.

### Offer Details
- Functions: save and edit the offer detail, including title, company... 
- Interactions: Invoked by `Offer Manager`, interacts with `Job Score Calculator` to provide value of each factor.

### Job Score Calculator
- Functions: calculate the score of each job offer
- Interactions: Invoked by `Offer Manager`, interacts with `Weights` and `Offer Details` to calculate the score of each factor.

The details about how to address requirements are described below.

Requirement 1: When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

To realize this requirement, I added `User Main Menu` class, which contains the main menu options to the user and captures their selection and interact with "APP Processing" 

Requirement 2: 
    When choosing to enter current job details, a user will:
        a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
            Title
            Company
            Location (entered as city and state)
            Cost of living in the location (expressed as an index)
            Yearly salary 
            Yearly bonus 
            Number of stock option shares offered
            Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary)
            Personal Choice Holidays (A single overall number of days from 0 to 20)
            Monthly Internet Stipend ($0 to $75 inclusive)
        b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
To realize this requirement, I added `APP Processing`, `Offer Manager` and `Offer Details` class. Once "Enter or edit current job details" is triggered on the main menu, it will trigger "Current Offer" in the `App Processing`, which will invoke `Offer Manager` to "edit current offer" and edit the `Offer Details`

Requirement 3: 
    When choosing to enter job offers, a user will:
        a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
        b. Be able to either save the job offer details or cancel.
        c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the
           current job details (if present).
To realize this requiement, I added `APP Processing"`, `Offer Manager` and `Offer Details` class. Once "Enter job offers" is clicked on the main menu, "Offer List" in the "APP Processing" will be triggerred. Then "enter new offer", "rank offer list" and "compare offers" will be triggerred. The user can edit, save, cancel the offer detail through "Offer Details". The user can also compare the offer through the "compare offers" in the `Offer Manager`

Requirement 4:
    When adjusting the comparison settings, the user can assign integer weights to:
        a. Yearly salary
        b. Yearly bonus
        c. Number of Stock Option Shares Offered
        d. Home Buying Program Fund
        e. Personal Choice Holidays
        f. Monthly Internet Stipend
    If no weights are assigned, all factors are considered equal.
To realize this requirement, I added "Adjust comparison settings" in the `User Main Menu`, which will trigger "Offer Comparison Settings" in `APP Processing`. Then the user can adjust the `Weights` accessed by "Offer Comparison Settings"

Requirement 5:
    When choosing to compare job offers, a user will:
        a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and 
           including the current job (if present), clearly indicated.
        b. Select two jobs to compare and trigger the comparison.
        c. Be shown a table comparing the two jobs, displaying, for each job:
            Title
            Company
            Location 
            Yearly salary adjusted for cost of living
            Yearly bonus adjusted for cost of living
            Number of Stock Option Shares Offered
            Home Buying Program fund (one-time up to 15% of Yearly Salary)
            Personal Choice Holidays (A single overall number of days from 0 to 20) 
            Monthly Internet Stipend ($0 to $75 inclusive monthly)
        d. Be offered to perform another comparison or go back to the main menu.
To realize this requirement, I added "Compare job offers" in the `User Main Menu`. It will trigger "Offer List" in the `APP Processing` to invoke "rank offer list", which will invoke `Job Score Calculator` to calculate score of each job offer and get ranking. The "rank offer list" will access `Offer Details` for each offer and present to users when two offers are selected. 

Requirement 6:
    When ranking jobs, a job’s score is computed as the weighted average of:

    AYS + AYB + (CSO/3) + HBP + (PCH * AYS / 260) + (MIS*12)

    where:
    AYS = yearly salary adjusted for cost of living
    AYB = yearly bonus adjusted for cost of living
    CSO = Company shares offered (assuming a 3-year vesting schedule and a price-per-share of $1)
    HBP = Home Buying Program
    PCH = Personal Choice Holidays 
    MIS= Monthly Internet Stipend 
To realize this requirement, I added `Job Score Calculator`, which interact with `Offer Details` and `Weights`. Then each offer score will be calculated accordingly.

Requirement 7: 
    The user interface must be intuitive and responsive.
This is not represented in my design, as it will be handled entirely within the GUI implementation

Requirement 8: 
    For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
I will assume there is single system running the app









