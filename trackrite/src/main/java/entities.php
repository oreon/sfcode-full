
<?php
	
/**
 * @MappedSuperclass
 */
class BusinessEntity {

    /**
     * @Id @Column(type="integer") @GeneratedValue
     */
    public $id;
    
    
    /**
     * @Column(type="datetime")
     */
    public $dateCreated;
}

	
	
/**
 * @MappedSuperclass
 */
class Person  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $firstName;
	
	
	public function getFirstName(){
		return $this->firstName;
	}
	
	public function setFirstName($firstName){
		$this->firstName = $firstName;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $lastName;
	
	
	public function getLastName(){
		return $this->lastName;
	}
	
	public function setLastName($lastName){
		$this->lastName = $lastName;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $user;
	
	
	public function getUser(){
		return $this->user;
	}
	
	public function setUser($user){
		$this->user = $user;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Employee")
 */
 
class Employee  extends org.wc.trackrite.domain.Person {	

	
	
		
	/**
     * @ManyToOne(targetEntity="Employee", inversedBy="employees")
     */
		
	
	private $department;
	
	
	public function getDepartment(){
		return $this->department;
	}
	
	public function setDepartment($department){
		$this->department = $department;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $employeeNumber;
	
	
	public function getEmployeeNumber(){
		return $this->employeeNumber;
	}
	
	public function setEmployeeNumber($employeeNumber){
		$this->employeeNumber = $employeeNumber;
	}
	
	
	
	
	
	/**
     * @Column(type="EmployeeType")
     * @var EmployeeType
     */
	
	private $employeeType;
	
	
	public function getEmployeeType(){
		return $this->employeeType;
	}
	
	public function setEmployeeType($employeeType){
		$this->employeeType = $employeeType;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Issue", mappedBy="developer")
     * @var Issue[]
     */
			
		
	
	private $issues;
	
	
	public function getIssues(){
		return $this->issues;
	}
	
	public function setIssues($issues){
		$this->issues = $issues;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $home;
	
	
	public function getHome(){
		return $this->home;
	}
	
	public function setHome($home){
		$this->home = $home;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="Project")
     * @var Project[]
     */
			
		
	
	private $projects;
	
	
	public function getProjects(){
		return $this->projects;
	}
	
	public function setProjects($projects){
		$this->projects = $projects;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Department")
 */
 
class Department  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="Employee", mappedBy="department")
     * @var Employee[]
     */
			
		
	
	private $employees;
	
	
	public function getEmployees(){
		return $this->employees;
	}
	
	public function setEmployees($employees){
		$this->employees = $employees;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="EndUser")
 */
 
class EndUser  extends org.wc.trackrite.domain.Person {	

	
}		


 
class ContactDetails  {	

	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $primaryPhone;
	
	
	public function getPrimaryPhone(){
		return $this->primaryPhone;
	}
	
	public function setPrimaryPhone($primaryPhone){
		$this->primaryPhone = $primaryPhone;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $secondaryPhone;
	
	
	public function getSecondaryPhone(){
		return $this->secondaryPhone;
	}
	
	public function setSecondaryPhone($secondaryPhone){
		$this->secondaryPhone = $secondaryPhone;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $city;
	
	
	public function getCity(){
		return $this->city;
	}
	
	public function setCity($city){
		$this->city = $city;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $streetAddress;
	
	
	public function getStreetAddress(){
		return $this->streetAddress;
	}
	
	public function setStreetAddress($streetAddress){
		$this->streetAddress = $streetAddress;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Project")
 */
 
class Project  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="Issue", mappedBy="project")
     * @var Issue[]
     */
			
		
	
	private $issues;
	
	
	public function getIssues(){
		return $this->issues;
	}
	
	public function setIssues($issues){
		$this->issues = $issues;
	}
	
	
	
	
	
	/**
     * @Column(type="uniqueNameType")
     * @var uniqueNameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $description;
	
	
	public function getDescription(){
		return $this->description;
	}
	
	public function setDescription($description){
		$this->description = $description;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="Employee")
     * @var Employee[]
     */
			
		
	
	private $employees;
	
	
	public function getEmployees(){
		return $this->employees;
	}
	
	public function setEmployees($employees){
		$this->employees = $employees;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Issue")
 */
 
class Issue  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $title;
	
	
	public function getTitle(){
		return $this->title;
	}
	
	public function setTitle($title){
		$this->title = $title;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $description;
	
	
	public function getDescription(){
		return $this->description;
	}
	
	public function setDescription($description){
		$this->description = $description;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Issue", inversedBy="issues")
     */
		
	
	private $project;
	
	
	public function getProject(){
		return $this->project;
	}
	
	public function setProject($project){
		$this->project = $project;
	}
	
	
	
	
	
	/**
     * @Column(type="Status")
     * @var Status
     */
	
	private $status;
	
	
	public function getStatus(){
		return $this->status;
	}
	
	public function setStatus($status){
		$this->status = $status;
	}
	
	
	
	
	
	/**
     * @Column(type="Priority")
     * @var Priority
     */
	
	private $priority;
	
	
	public function getPriority(){
		return $this->priority;
	}
	
	public function setPriority($priority){
		$this->priority = $priority;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Issue", inversedBy="issues")
     */
		
	
	private $developer;
	
	
	public function getDeveloper(){
		return $this->developer;
	}
	
	public function setDeveloper($developer){
		$this->developer = $developer;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $closeTime;
	
	
	public function getCloseTime(){
		return $this->closeTime;
	}
	
	public function setCloseTime($closeTime){
		$this->closeTime = $closeTime;
	}
	
	
	
	
	
	/**
     * @Column(type="Integer")
     * @var Integer
     */
	
	private $estimate;
	
	
	public function getEstimate(){
		return $this->estimate;
	}
	
	public function setEstimate($estimate){
		$this->estimate = $estimate;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $creator;
	
	
	public function getCreator(){
		return $this->creator;
	}
	
	public function setCreator($creator){
		$this->creator = $creator;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $category;
	
	
	public function getCategory(){
		return $this->category;
	}
	
	public function setCategory($category){
		$this->category = $category;
	}
	
	
	
	
	
	/**
     * @Column(type="imageFile")
     * @var imageFile
     */
	
	private $file;
	
	
	public function getFile(){
		return $this->file;
	}
	
	public function setFile($file){
		$this->file = $file;
	}
	
	
	
	
	
	/**
     * @Column(type="Severity")
     * @var Severity
     */
	
	private $severity;
	
	
	public function getSeverity(){
		return $this->severity;
	}
	
	public function setSeverity($severity){
		$this->severity = $severity;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $qa;
	
	
	public function getQa(){
		return $this->qa;
	}
	
	public function setQa($qa){
		$this->qa = $qa;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="MilestoneRelease")
     * @var MilestoneRelease[]
     */
			
		
	
	private $milestoneReleases;
	
	
	public function getMilestoneReleases(){
		return $this->milestoneReleases;
	}
	
	public function setMilestoneReleases($milestoneReleases){
		$this->milestoneReleases = $milestoneReleases;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Module")
 */
 
class Module  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Category")
 */
 
class Category  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="uniqueNameType")
     * @var uniqueNameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="MilestoneRelease")
 */
 
class MilestoneRelease  extends  BusinessEntity  {	

	
	
	/**
     * @ManyToMany(targetEntity="Issue")
     * @var Issue[]
     */
			
		
	
	private $issues;
	
	
	public function getIssues(){
		return $this->issues;
	}
	
	public function setIssues($issues){
		$this->issues = $issues;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $dueDate;
	
	
	public function getDueDate(){
		return $this->dueDate;
	}
	
	public function setDueDate($dueDate){
		$this->dueDate = $dueDate;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $comments;
	
	
	public function getComments(){
		return $this->comments;
	}
	
	public function setComments($comments){
		$this->comments = $comments;
	}
	
	
	
}		


/**
 * @Entity @Table(name="User")
 */
 
class User  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $userName;
	
	
	public function getUserName(){
		return $this->userName;
	}
	
	public function setUserName($userName){
		$this->userName = $userName;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $password;
	
	
	public function getPassword(){
		return $this->password;
	}
	
	public function setPassword($password){
		$this->password = $password;
	}
	
	
	
	
	
	/**
     * @Column(type="Boolean")
     * @var Boolean
     */
	
	private $enabled;
	
	
	public function getEnabled(){
		return $this->enabled;
	}
	
	public function setEnabled($enabled){
		$this->enabled = $enabled;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="Role")
     * @var Role[]
     */
			
		
	
	private $roles;
	
	
	public function getRoles(){
		return $this->roles;
	}
	
	public function setRoles($roles){
		$this->roles = $roles;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $email;
	
	
	public function getEmail(){
		return $this->email;
	}
	
	public function setEmail($email){
		$this->email = $email;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $lastLogin;
	
	
	public function getLastLogin(){
		return $this->lastLogin;
	}
	
	public function setLastLogin($lastLogin){
		$this->lastLogin = $lastLogin;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Role")
 */
 
class Role  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="User")
     * @var User[]
     */
			
		
	
	private $users;
	
	
	public function getUsers(){
		return $this->users;
	}
	
	public function setUsers($users){
		$this->users = $users;
	}
	
	
	
}		


/**
 * @Entity @Table(name="TimeTrackingEntry")
 */
 
class TimeTrackingEntry  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="Integer")
     * @var Integer
     */
	
	private $hours;
	
	
	public function getHours(){
		return $this->hours;
	}
	
	public function setHours($hours){
		$this->hours = $hours;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $details;
	
	
	public function getDetails(){
		return $this->details;
	}
	
	public function setDetails($details){
		$this->details = $details;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $issue;
	
	
	public function getIssue(){
		return $this->issue;
	}
	
	public function setIssue($issue){
		$this->issue = $issue;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="TimeTrackingEntry", inversedBy="timeTrackingEntrys")
     */
		
	
	private $timeSheet;
	
	
	public function getTimeSheet(){
		return $this->timeSheet;
	}
	
	public function setTimeSheet($timeSheet){
		$this->timeSheet = $timeSheet;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $date;
	
	
	public function getDate(){
		return $this->date;
	}
	
	public function setDate($date){
		$this->date = $date;
	}
	
	
	
}		


/**
 * @Entity @Table(name="TimeSheet")
 */
 
class TimeSheet  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="TimeTrackingEntry", mappedBy="timeSheet")
     * @var TimeTrackingEntry[]
     */
			
		
	
	private $timeTrackingEntrys;
	
	
	public function getTimeTrackingEntrys(){
		return $this->timeTrackingEntrys;
	}
	
	public function setTimeTrackingEntrys($timeTrackingEntrys){
		$this->timeTrackingEntrys = $timeTrackingEntrys;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $title;
	
	
	public function getTitle(){
		return $this->title;
	}
	
	public function setTitle($title){
		$this->title = $title;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Exam")
 */
 
class Exam  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="uniqueNameType")
     * @var uniqueNameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
	
	
	/**
     * @Column(type="Integer")
     * @var Integer
     */
	
	private $duration;
	
	
	public function getDuration(){
		return $this->duration;
	}
	
	public function setDuration($duration){
		$this->duration = $duration;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Question", mappedBy="exam")
     * @var Question[]
     */
			
		
	
	private $questions;
	
	
	public function getQuestions(){
		return $this->questions;
	}
	
	public function setQuestions($questions){
		$this->questions = $questions;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Question")
 */
 
class Question  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $text;
	
	
	public function getText(){
		return $this->text;
	}
	
	public function setText($text){
		$this->text = $text;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Choice", mappedBy="question")
     * @var Choice[]
     */
			
		
	
	private $choices;
	
	
	public function getChoices(){
		return $this->choices;
	}
	
	public function setChoices($choices){
		$this->choices = $choices;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Question", inversedBy="questions")
     */
		
	
	private $exam;
	
	
	public function getExam(){
		return $this->exam;
	}
	
	public function setExam($exam){
		$this->exam = $exam;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Choice")
 */
 
class Choice  extends  BusinessEntity  {	

	
	
		
	/**
     * @ManyToOne(targetEntity="Choice", inversedBy="choices")
     */
		
	
	private $question;
	
	
	public function getQuestion(){
		return $this->question;
	}
	
	public function setQuestion($question){
		$this->question = $question;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $text;
	
	
	public function getText(){
		return $this->text;
	}
	
	public function setText($text){
		$this->text = $text;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Candidate")
 */
 
class Candidate  extends  BusinessEntity  {	

	
}		


/**
 * @Entity @Table(name="ExamInstance")
 */
 
class ExamInstance  extends  BusinessEntity  {	

	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $exam;
	
	
	public function getExam(){
		return $this->exam;
	}
	
	public function setExam($exam){
		$this->exam = $exam;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $candidate;
	
	
	public function getCandidate(){
		return $this->candidate;
	}
	
	public function setCandidate($candidate){
		$this->candidate = $candidate;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Answer", mappedBy="examInstance")
     * @var Answer[]
     */
			
		
	
	private $answers;
	
	
	public function getAnswers(){
		return $this->answers;
	}
	
	public function setAnswers($answers){
		$this->answers = $answers;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Answer")
 */
 
class Answer  extends  BusinessEntity  {	

	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $choice;
	
	
	public function getChoice(){
		return $this->choice;
	}
	
	public function setChoice($choice){
		$this->choice = $choice;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Answer", inversedBy="answers")
     */
		
	
	private $examInstance;
	
	
	public function getExamInstance(){
		return $this->examInstance;
	}
	
	public function setExamInstance($examInstance){
		$this->examInstance = $examInstance;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Schedule")
 */
 
class Schedule  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="ScheduleItem", mappedBy="")
     * @var ScheduleItem[]
     */
			
		
	
	private $scheduleItems;
	
	
	public function getScheduleItems(){
		return $this->scheduleItems;
	}
	
	public function setScheduleItems($scheduleItems){
		$this->scheduleItems = $scheduleItems;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $project;
	
	
	public function getProject(){
		return $this->project;
	}
	
	public function setProject($project){
		$this->project = $project;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="ScheduleItem")
 */
 
class ScheduleItem  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $startDate;
	
	
	public function getStartDate(){
		return $this->startDate;
	}
	
	public function setStartDate($startDate){
		$this->startDate = $startDate;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $endDate;
	
	
	public function getEndDate(){
		return $this->endDate;
	}
	
	public function setEndDate($endDate){
		$this->endDate = $endDate;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $employee;
	
	
	public function getEmployee(){
		return $this->employee;
	}
	
	public function setEmployee($employee){
		$this->employee = $employee;
	}
	
	
	
}		


	

	
/**
 * @MappedSuperclass
 */
 
class Person  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $firstName;
	
	
	public function getFirstName(){
		return $this->firstName;
	}
	
	public function setFirstName($firstName){
		$this->firstName = $firstName;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $lastName;
	
	
	public function getLastName(){
		return $this->lastName;
	}
	
	public function setLastName($lastName){
		$this->lastName = $lastName;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $user;
	
	
	public function getUser(){
		return $this->user;
	}
	
	public function setUser($user){
		$this->user = $user;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Employee")
 */
 
class Employee  extends org.wc.trackrite.domain.Person {	

	
	
		
	/**
     * @ManyToOne(targetEntity="Employee", inversedBy="employees")
     */
		
	
	private $department;
	
	
	public function getDepartment(){
		return $this->department;
	}
	
	public function setDepartment($department){
		$this->department = $department;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $employeeNumber;
	
	
	public function getEmployeeNumber(){
		return $this->employeeNumber;
	}
	
	public function setEmployeeNumber($employeeNumber){
		$this->employeeNumber = $employeeNumber;
	}
	
	
	
	
	
	/**
     * @Column(type="EmployeeType")
     * @var EmployeeType
     */
	
	private $employeeType;
	
	
	public function getEmployeeType(){
		return $this->employeeType;
	}
	
	public function setEmployeeType($employeeType){
		$this->employeeType = $employeeType;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Issue", mappedBy="developer")
     * @var Issue[]
     */
			
		
	
	private $issues;
	
	
	public function getIssues(){
		return $this->issues;
	}
	
	public function setIssues($issues){
		$this->issues = $issues;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $home;
	
	
	public function getHome(){
		return $this->home;
	}
	
	public function setHome($home){
		$this->home = $home;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="Project")
     * @var Project[]
     */
			
		
	
	private $projects;
	
	
	public function getProjects(){
		return $this->projects;
	}
	
	public function setProjects($projects){
		$this->projects = $projects;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Department")
 */
 
class Department  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="Employee", mappedBy="department")
     * @var Employee[]
     */
			
		
	
	private $employees;
	
	
	public function getEmployees(){
		return $this->employees;
	}
	
	public function setEmployees($employees){
		$this->employees = $employees;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="EndUser")
 */
 
class EndUser  extends org.wc.trackrite.domain.Person {	

	
}		


 
class ContactDetails  {	

	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $primaryPhone;
	
	
	public function getPrimaryPhone(){
		return $this->primaryPhone;
	}
	
	public function setPrimaryPhone($primaryPhone){
		$this->primaryPhone = $primaryPhone;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $secondaryPhone;
	
	
	public function getSecondaryPhone(){
		return $this->secondaryPhone;
	}
	
	public function setSecondaryPhone($secondaryPhone){
		$this->secondaryPhone = $secondaryPhone;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $city;
	
	
	public function getCity(){
		return $this->city;
	}
	
	public function setCity($city){
		$this->city = $city;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $streetAddress;
	
	
	public function getStreetAddress(){
		return $this->streetAddress;
	}
	
	public function setStreetAddress($streetAddress){
		$this->streetAddress = $streetAddress;
	}
	
	
	
}		


	

	
/**
 * @Entity @Table(name="Project")
 */
 
class Project  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="Issue", mappedBy="project")
     * @var Issue[]
     */
			
		
	
	private $issues;
	
	
	public function getIssues(){
		return $this->issues;
	}
	
	public function setIssues($issues){
		$this->issues = $issues;
	}
	
	
	
	
	
	/**
     * @Column(type="uniqueNameType")
     * @var uniqueNameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $description;
	
	
	public function getDescription(){
		return $this->description;
	}
	
	public function setDescription($description){
		$this->description = $description;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="Employee")
     * @var Employee[]
     */
			
		
	
	private $employees;
	
	
	public function getEmployees(){
		return $this->employees;
	}
	
	public function setEmployees($employees){
		$this->employees = $employees;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Issue")
 */
 
class Issue  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $title;
	
	
	public function getTitle(){
		return $this->title;
	}
	
	public function setTitle($title){
		$this->title = $title;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $description;
	
	
	public function getDescription(){
		return $this->description;
	}
	
	public function setDescription($description){
		$this->description = $description;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Issue", inversedBy="issues")
     */
		
	
	private $project;
	
	
	public function getProject(){
		return $this->project;
	}
	
	public function setProject($project){
		$this->project = $project;
	}
	
	
	
	
	
	/**
     * @Column(type="Status")
     * @var Status
     */
	
	private $status;
	
	
	public function getStatus(){
		return $this->status;
	}
	
	public function setStatus($status){
		$this->status = $status;
	}
	
	
	
	
	
	/**
     * @Column(type="Priority")
     * @var Priority
     */
	
	private $priority;
	
	
	public function getPriority(){
		return $this->priority;
	}
	
	public function setPriority($priority){
		$this->priority = $priority;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Issue", inversedBy="issues")
     */
		
	
	private $developer;
	
	
	public function getDeveloper(){
		return $this->developer;
	}
	
	public function setDeveloper($developer){
		$this->developer = $developer;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $closeTime;
	
	
	public function getCloseTime(){
		return $this->closeTime;
	}
	
	public function setCloseTime($closeTime){
		$this->closeTime = $closeTime;
	}
	
	
	
	
	
	/**
     * @Column(type="Integer")
     * @var Integer
     */
	
	private $estimate;
	
	
	public function getEstimate(){
		return $this->estimate;
	}
	
	public function setEstimate($estimate){
		$this->estimate = $estimate;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $creator;
	
	
	public function getCreator(){
		return $this->creator;
	}
	
	public function setCreator($creator){
		$this->creator = $creator;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $category;
	
	
	public function getCategory(){
		return $this->category;
	}
	
	public function setCategory($category){
		$this->category = $category;
	}
	
	
	
	
	
	/**
     * @Column(type="imageFile")
     * @var imageFile
     */
	
	private $file;
	
	
	public function getFile(){
		return $this->file;
	}
	
	public function setFile($file){
		$this->file = $file;
	}
	
	
	
	
	
	/**
     * @Column(type="Severity")
     * @var Severity
     */
	
	private $severity;
	
	
	public function getSeverity(){
		return $this->severity;
	}
	
	public function setSeverity($severity){
		$this->severity = $severity;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $qa;
	
	
	public function getQa(){
		return $this->qa;
	}
	
	public function setQa($qa){
		$this->qa = $qa;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="MilestoneRelease")
     * @var MilestoneRelease[]
     */
			
		
	
	private $milestoneReleases;
	
	
	public function getMilestoneReleases(){
		return $this->milestoneReleases;
	}
	
	public function setMilestoneReleases($milestoneReleases){
		$this->milestoneReleases = $milestoneReleases;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Module")
 */
 
class Module  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Category")
 */
 
class Category  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="uniqueNameType")
     * @var uniqueNameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="MilestoneRelease")
 */
 
class MilestoneRelease  extends  BusinessEntity  {	

	
	
	/**
     * @ManyToMany(targetEntity="Issue")
     * @var Issue[]
     */
			
		
	
	private $issues;
	
	
	public function getIssues(){
		return $this->issues;
	}
	
	public function setIssues($issues){
		$this->issues = $issues;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $dueDate;
	
	
	public function getDueDate(){
		return $this->dueDate;
	}
	
	public function setDueDate($dueDate){
		$this->dueDate = $dueDate;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $comments;
	
	
	public function getComments(){
		return $this->comments;
	}
	
	public function setComments($comments){
		$this->comments = $comments;
	}
	
	
	
}		


	

	
/**
 * @Entity @Table(name="User")
 */
 
class User  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $userName;
	
	
	public function getUserName(){
		return $this->userName;
	}
	
	public function setUserName($userName){
		$this->userName = $userName;
	}
	
	
	
	
	
	/**
     * @Column(type="String")
     * @var String
     */
	
	private $password;
	
	
	public function getPassword(){
		return $this->password;
	}
	
	public function setPassword($password){
		$this->password = $password;
	}
	
	
	
	
	
	/**
     * @Column(type="Boolean")
     * @var Boolean
     */
	
	private $enabled;
	
	
	public function getEnabled(){
		return $this->enabled;
	}
	
	public function setEnabled($enabled){
		$this->enabled = $enabled;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="Role")
     * @var Role[]
     */
			
		
	
	private $roles;
	
	
	public function getRoles(){
		return $this->roles;
	}
	
	public function setRoles($roles){
		$this->roles = $roles;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $email;
	
	
	public function getEmail(){
		return $this->email;
	}
	
	public function setEmail($email){
		$this->email = $email;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $lastLogin;
	
	
	public function getLastLogin(){
		return $this->lastLogin;
	}
	
	public function setLastLogin($lastLogin){
		$this->lastLogin = $lastLogin;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Role")
 */
 
class Role  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
	
	/**
     * @ManyToMany(targetEntity="User")
     * @var User[]
     */
			
		
	
	private $users;
	
	
	public function getUsers(){
		return $this->users;
	}
	
	public function setUsers($users){
		$this->users = $users;
	}
	
	
	
}		


	

	
/**
 * @Entity @Table(name="TimeTrackingEntry")
 */
 
class TimeTrackingEntry  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="Integer")
     * @var Integer
     */
	
	private $hours;
	
	
	public function getHours(){
		return $this->hours;
	}
	
	public function setHours($hours){
		$this->hours = $hours;
	}
	
	
	
	
	
	/**
     * @Column(type="largeText")
     * @var largeText
     */
	
	private $details;
	
	
	public function getDetails(){
		return $this->details;
	}
	
	public function setDetails($details){
		$this->details = $details;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $issue;
	
	
	public function getIssue(){
		return $this->issue;
	}
	
	public function setIssue($issue){
		$this->issue = $issue;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="TimeTrackingEntry", inversedBy="timeTrackingEntrys")
     */
		
	
	private $timeSheet;
	
	
	public function getTimeSheet(){
		return $this->timeSheet;
	}
	
	public function setTimeSheet($timeSheet){
		$this->timeSheet = $timeSheet;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $date;
	
	
	public function getDate(){
		return $this->date;
	}
	
	public function setDate($date){
		$this->date = $date;
	}
	
	
	
}		


/**
 * @Entity @Table(name="TimeSheet")
 */
 
class TimeSheet  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="TimeTrackingEntry", mappedBy="timeSheet")
     * @var TimeTrackingEntry[]
     */
			
		
	
	private $timeTrackingEntrys;
	
	
	public function getTimeTrackingEntrys(){
		return $this->timeTrackingEntrys;
	}
	
	public function setTimeTrackingEntrys($timeTrackingEntrys){
		$this->timeTrackingEntrys = $timeTrackingEntrys;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $title;
	
	
	public function getTitle(){
		return $this->title;
	}
	
	public function setTitle($title){
		$this->title = $title;
	}
	
	
	
}		


	

	
	

	
/**
 * @Entity @Table(name="Exam")
 */
 
class Exam  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="uniqueNameType")
     * @var uniqueNameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
	
	
	/**
     * @Column(type="Integer")
     * @var Integer
     */
	
	private $duration;
	
	
	public function getDuration(){
		return $this->duration;
	}
	
	public function setDuration($duration){
		$this->duration = $duration;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Question", mappedBy="exam")
     * @var Question[]
     */
			
		
	
	private $questions;
	
	
	public function getQuestions(){
		return $this->questions;
	}
	
	public function setQuestions($questions){
		$this->questions = $questions;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Question")
 */
 
class Question  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $text;
	
	
	public function getText(){
		return $this->text;
	}
	
	public function setText($text){
		$this->text = $text;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Choice", mappedBy="question")
     * @var Choice[]
     */
			
		
	
	private $choices;
	
	
	public function getChoices(){
		return $this->choices;
	}
	
	public function setChoices($choices){
		$this->choices = $choices;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Question", inversedBy="questions")
     */
		
	
	private $exam;
	
	
	public function getExam(){
		return $this->exam;
	}
	
	public function setExam($exam){
		$this->exam = $exam;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Choice")
 */
 
class Choice  extends  BusinessEntity  {	

	
	
		
	/**
     * @ManyToOne(targetEntity="Choice", inversedBy="choices")
     */
		
	
	private $question;
	
	
	public function getQuestion(){
		return $this->question;
	}
	
	public function setQuestion($question){
		$this->question = $question;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $text;
	
	
	public function getText(){
		return $this->text;
	}
	
	public function setText($text){
		$this->text = $text;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Candidate")
 */
 
class Candidate  extends  BusinessEntity  {	

	
}		


/**
 * @Entity @Table(name="ExamInstance")
 */
 
class ExamInstance  extends  BusinessEntity  {	

	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $exam;
	
	
	public function getExam(){
		return $this->exam;
	}
	
	public function setExam($exam){
		$this->exam = $exam;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $candidate;
	
	
	public function getCandidate(){
		return $this->candidate;
	}
	
	public function setCandidate($candidate){
		$this->candidate = $candidate;
	}
	
	
	
	
			
	 /**
     * @OneToMany(targetEntity="Answer", mappedBy="examInstance")
     * @var Answer[]
     */
			
		
	
	private $answers;
	
	
	public function getAnswers(){
		return $this->answers;
	}
	
	public function setAnswers($answers){
		$this->answers = $answers;
	}
	
	
	
}		


/**
 * @Entity @Table(name="Answer")
 */
 
class Answer  extends  BusinessEntity  {	

	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $choice;
	
	
	public function getChoice(){
		return $this->choice;
	}
	
	public function setChoice($choice){
		$this->choice = $choice;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="Answer", inversedBy="answers")
     */
		
	
	private $examInstance;
	
	
	public function getExamInstance(){
		return $this->examInstance;
	}
	
	public function setExamInstance($examInstance){
		$this->examInstance = $examInstance;
	}
	
	
	
}		


	

	
	

	
/**
 * @Entity @Table(name="Schedule")
 */
 
class Schedule  extends  BusinessEntity  {	

	
	
			
	 /**
     * @OneToMany(targetEntity="ScheduleItem", mappedBy="")
     * @var ScheduleItem[]
     */
			
		
	
	private $scheduleItems;
	
	
	public function getScheduleItems(){
		return $this->scheduleItems;
	}
	
	public function setScheduleItems($scheduleItems){
		$this->scheduleItems = $scheduleItems;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $project;
	
	
	public function getProject(){
		return $this->project;
	}
	
	public function setProject($project){
		$this->project = $project;
	}
	
	
	
	
	
	/**
     * @Column(type="nameType")
     * @var nameType
     */
	
	private $name;
	
	
	public function getName(){
		return $this->name;
	}
	
	public function setName($name){
		$this->name = $name;
	}
	
	
	
}		


/**
 * @Entity @Table(name="ScheduleItem")
 */
 
class ScheduleItem  extends  BusinessEntity  {	

	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $startDate;
	
	
	public function getStartDate(){
		return $this->startDate;
	}
	
	public function setStartDate($startDate){
		$this->startDate = $startDate;
	}
	
	
	
	
	
	/**
     * @Column(type="Date")
     * @var Date
     */
	
	private $endDate;
	
	
	public function getEndDate(){
		return $this->endDate;
	}
	
	public function setEndDate($endDate){
		$this->endDate = $endDate;
	}
	
	
	
	
		
	/**
     * @ManyToOne(targetEntity="", inversedBy="")
     */
		
	
	private $employee;
	
	
	public function getEmployee(){
		return $this->employee;
	}
	
	public function setEmployee($employee){
		$this->employee = $employee;
	}
	
	
	
}		


	

	
	

	
	

	
	

	
	

	 
?>
