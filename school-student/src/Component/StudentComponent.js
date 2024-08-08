import axios from "axios";
import React, { useEffect, useState } from "react";

const StudentComponent = () => {
    const [students, setStudents] = useState([]);
    const [studentId,setStudentId] = useState("");
    const [schoolId,setSchoolId] = useState("");
    const [firstname,setFirstname] = useState("");
    const [lastname,setLastname] = useState("");
    const [email,setEmail] = useState("");
    const [dob,setDob] = useState("");
    const [edit,setEdit] = useState(false);

    const handleClick = (e) => {
        e.preventDefault();
        studentList();
    }

    const studentList = () => {
        axios.get("http://localhost:8082/api/students")
            .then(response => {
                setStudents(response.data);
                console.log("Students data fetch successfully");
            })
            .catch(error =>
                console.log("Error for fecting the students", error)
            );
    }

    const handleGetStudentsBySchool = (e,schoolId) => {
        e.preventDefault();
        axios.get(`http://localhost:8082/api/by-school/${schoolId}`)
            .then(response => {
                setStudents(response.data);
                console.log("data fetch successfully");
            })
            .catch(error =>
                console.log("Error for fecting the students", error)
            );
    }

    const handleSearchStudentsByName = (e,name) => {
        e.preventDefault();
        axios.get(`http://localhost:8082/api/students/search?`,{
            params : {name}
        })
            .then(response => {
                setStudents(response.data);
                console.log("Students Name data fetch successfully");
            })
            .catch(error =>
                console.log("Error for fecting the students", error)
            );
    }


    const handleEditStudent = (e,index) => {
        e.preventDefault();
        setEdit(true);

        setStudentId(students[index].id);
        setFirstname(students[index].firstname);
        setLastname(students[index].lastname);
        setDob(students[index].dob);
        setSchoolId(students[index].schoolId);
    }

    const handleDeleteStudent = (e,index) => {
        e.preventDefault();
        const studentId = students[index].id;
        axios.delete(`http://localhost:8082/api/students/${studentId}`)
        .then( response => {
            console.log("Student got Deleteld");
            studentList();
        })
        .catch(error => {
            console.error("Error occured",error);
        })
    }

    useEffect(() => {
        studentList();
    },[]);

    const handleAddAndEditStudent = (e) =>  {
        e.preventDefault();
        if(edit) {
            axios.put(`http://localhost:8082/api/students/${studentId}`,{
                firstname:firstname,
                lastname:lastname,
                email:email,
                dob:dob,
                schoolId:schoolId
            })
            .then(response => {
            console.log("student got updated sucessfully");
            studentList();
        })
        .catch(error => {
            console.error("Error occured",error);
        })
        setEdit(false);
        setStudentId("");
        setFirstname("");
        setLastname("");
        setEmail("");
        setDob("");
        setSchoolId("");
        } 
        else {
        axios.post("http://localhost:8082/api/student",{
            firstname:firstname,
            lastname:lastname,
            email:email,
            dob:dob,
            schoolId:schoolId
        })
        .then(response => {
            console.log("student added sucessfully");
            studentList();
        })
        .catch(error => {
            console.error("Error occured",error);
        })
    }
    }
    return (
        <div>
            <h2>Students List</h2>
            <br/>
             <h3>Show the List of Students based on School Id</h3>
               Search by School Id : <input type="text" name="schoolId" onChange={(e) => handleGetStudentsBySchool(e,e.target.value)} />
            <br/> 
            <br/>
            <h3>Show the List of Students based on Name</h3>
              Search Students By Name : <input type="text" name="firstname" onChange={(e) => handleSearchStudentsByName(e,e.target.value)} />
            <br/>
            <br/>
            <button type="submit" onClick={handleClick} className="btn btn-primary">Show Students </button>
            <br/>
            <br/>
            <form>
                <table border="4" align="center" className="container mt-1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Eamil</th>
                            <th>Date Of Birth</th>
                            <th>School ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {students && students.map((student,index) => (
                            <tr key={student.id}>
                                <td>{student.id}</td>
                                <td>{student.firstname}</td>
                                <td>{student.lastname}</td>
                                <td>{student.email}</td>
                                <td>{student.dob}</td>
                                <td>{student.schoolId}</td>
                                <td><button type="submit" onClick={(e) => handleEditStudent(e,index)} className="btn btn-primary"> Edit </button>&nbsp;&nbsp;&nbsp;
                                    <button type="submit" onClick={(e) => handleDeleteStudent(e,index)} className="btn btn-primary">Delete</button></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <br/>
                First Name : <input type="text" className="form-control" value={firstname} onChange={(e) => setFirstname(e.target.value)}/> <br/>
                Last Name : <input type="text" className="form-control" value={lastname} onChange={(e) => setLastname(e.target.value)}/> <br/>
                Email : <input type="text" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)}/> <br/>
                Date Of Birth : <input type="text" className="form-control" value={dob} onChange={(e) => setDob(e.target.value)}/> <br/>
                School Id : <input type="text"  className="form-control" value={schoolId} onChange={(e) => setSchoolId(e.target.value)}/> <br/>
                <br/>
               <button type="submit" onClick={handleAddAndEditStudent} className="btn btn-primary"> {edit ? "Update" : "Add"}</button> 
            </form>
        </div>
    )
}

export default StudentComponent;