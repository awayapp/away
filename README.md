# away
A leave management system.

The structure and the tedious elements of the application were generated using JHipster 5.2.1.

## Main goals
This is a playground application to test and learn various software development techniques, covering mainly:

- Implement business logic (applying EJ items)
- Cover backend with unit tests (JUnit and Spock) and integration tests
- Ship with Docker
- Deploy to AWS
- Send messages to the notification app with Kafka
- Implement an UI (with React)

## Modules
### 1. Core
The main backend app exposing REST interfaces for the core activities of the system.

From a business perspective, the system distinguishes 3 types of users:
1. `root` (unique and pre-configured in the shipped DB, in charge of configuring the system technical details),
1. `manager` - an employee of the system with leave approval rights,
1. and `employee` - an employee of the system without leave approval rights.

All employees of the system, that is users with either `employee` or `manager` roles associated, can request a leave.

A manager can be her own manager.

The app ships with a default `root` user identified by the following credentials:

- Email: `root@awayapp.io`
- Password: `away_root3%$!#`

#### Supported operations

##### 1. Create an employee
Only a `manager` can create a new employee.

The required employee details are as follows:

- First name
- Last name
- Email address
    - mandatory, and must be a valid email address, and unique in the system
- Hire date
    - mandatory, and must be a valid date
- Maximum number of vacation days per calendar year
    - mandatory, and must be a number higher than 20
- Is manager?
    - if checked, the `manager` role is assigned to this employee
- Manager
    - not mandatory
    - if specified, must point to an employee with `manager` rights
- Team
    - not mandatory

The email address and the password represent the login credentials. When a new employee is created... **TBD: activation link, temp passowrd, etc.**.
<br/>
<br/>

##### 2. Edit an employee
Only a `manager` can edit an employee.

All the employee details can be changed, and the validation rules from the creation of an employee are maintained.

If the **number of maximum vacation days per year** is changed, and the new maximum number of vacation days exceeds the sum of leaves of type `VACATION`, the operation is not permitted.

If the **manager** is changed, and the employee has pending leave requests, the newly assigned manager will be notified of those as if new leave requests have been made. As a consequence, the former manager of this employee will no longer have the right to approve those leave requests.
<br/>
<br/>

##### 3. Delete an employee
Only a `manager` can delete an employee.

When an employee is deleted, he will no longer appear in a team listing, nor in the manager employees' listing, and all his leave requests will also be removed from the system.
<br/>
<br/>

##### 4. Create a team
Only a `manager` can create a team.

The only property of a team is the `name` which is a mandatory string of more than one character.
<br/>
<br/>

##### 5. Edit a team
Only a `manager` can edit a team.

The only thing to edit in a team is its `name`, and the validation rules imposed when creating one apply at editing as well.
<br/>
<br/>

##### 6. Delete a team
Only a `manager` can delete a team.

If the team has employees associated to it, deletion is not allowed. When a team is deleted, it is completely removed from the system, it will no longer appear in team-related reports, nor as an option when creating or editing an employee.
<br/>
<br/>

##### 7. Request leave
All the employees in the system, that have a manager, can request a leave. Employees with no manager cannot request a leave.

Each employee is assigned a maximum number of leave days of type `VACATION` per calendar year. The calendar year starts 1st of January and ends 31st of December.

Based on the maximum number of leave days, each employee *produces* a certain amount of `VACATION` days per day. If by the time a new leave request is due, the number of produced `VACATION` days are not enough, a warning is included in the notification to the approver. The manager can decide to approve the request, this being considered giving vacation days in advance.

An employee is not allowed to take more `VACATION` leave days than he can produce in a given calendar year. Vacation days from the next calendar year cannot be given in advance. So for example if an employee was hired on 1st of July, has 30 vacation days per year, and by 15th of December has taken all the 15 days she can produce in this calendar year, any leave request of type `VACATION` planned to happen this year will be denied by the system until 1st of January next year.

The `VACATION` days of the previous calendar year must be consumed until the 31st of March the next year. This means they must happen until the end of March, *not* just be requested.

These are the properties of a new leave request:

- Start date
    - mandatory, and must point to a valid date
    - can be in the past
- End date
    - mandatory, and must point to a valid date
    - must be later than the start date
    - can be in the past
- Type
    - mandatory
    - one of: `VACATION`, `SICK`, `PARENTAL`

The system subtracts overlapping Sundays and Saturdays, and replies with the total number of leave days involved in this request.

The system does not support half-day requests.

The system ignores public holidays.

When the leave is successfully submitted, the manager of the employee is notified by email of the new request.
<br/>
<br/>

##### 8. View leaves requested by the logged user
All the employees have the option to check the status of all their leave requests.

The system provides the following details for each leave:
- Leave request date and time
- Start date
- End date
- Number of days
    - The system subtracts Saturdays and Sundays
- Type
- Status
    - one of: `PENDING`, `APPROVED`, `REJECTED`
<br/>
<br/>

##### 9. View statistics for the logged user
All employees can request statistics about their leaves.

The system replies with a list of years since hire date, plus the next calendar year, each year category including the following:
- Maximum number of vacation days per calendar year
- Number of vacation days produced until the current date
- Number of vacation days requested and `APPROVED`
    - With a warning if it's more than the days produced
- Number of available (max days - approved days) days to be taken until 31st of March next year
<br/>
<br/>

##### 10. View leaves that need approval by the logged user (manager)
Only a `manager` can get a list of leave requests that are assigned to him for approval.

The system provides the following details for each leave:
- Employee first name, last name, 
- Leave request date and time
- Start date
- End date
- Type
- Status
    - one of: `PENDING`, `APPROVED`, `REJECTED`
<br/>
<br/>

##### 11. Approve or deny a leave request
Only a `manager` can approve or deny a leave request.

Until the request is reviewed by a manager, it will have the `PENDING` status. Based on the decision of the manager, the status of the request is changed to either `APPROVED` or `REJECTED`.

When a leave request is approved or denied, the employee that requested it is being notified by email.
<br/>
<br/>

##### 12. Get leave per team per time interval
**TBD**
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

### 2. Notification
The backend app that handles external communication with users.

Actions:
- Send email to the manager when an absence of an employee of his team is requested
- Send email to the user when an absence he requested is approved/declined

### 3. Webapp
An Angular webapp.