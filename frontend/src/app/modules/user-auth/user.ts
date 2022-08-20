export class User {
    userId:string;
    password:string;
    firstName:string;
    lastName:string;
    constructor(userId:string,password:string,firstName:string,lastName:string){
        this.userId=userId;
        this.password=password;
         this.firstName=firstName;
         this.lastName=lastName;
     }
}