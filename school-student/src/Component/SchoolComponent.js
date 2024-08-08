import axios from "axios";
import React, { useEffect, useState } from "react";

const SchoolComponent = () => {
    const [schools, setSchools] = useState([]);
    const [schoolId,setSchoolId] = useState("");
    const [schoolLocation,setSchoolLocation] = useState("");
    const [name,setName] = useState("");
    const [location,setLocation] = useState("");
    const [edit,setEdit] = useState(false);

    const handleClick = (e) => {
        e.preventDefault();
        schoolList();
    }

    const schoolList = () => {
        axios.get("http://localhost:8083/api/schools")
            .then(response => {
                setSchools(response.data);
                console.log("List of Schools data fetch successfully");
            })
            .catch(error =>
                console.log("Error for fecting the users", error)
            );
    }

    const handleGetSchoolsByLocation = (e,location) => {
        e.preventDefault();
        axios.get(`http://localhost:8083/api/schools/by-location/${location}`)
            .then(response => {
                setSchools(response.data);
                console.log("data fetch successfully");
            })
            .catch(error =>
                console.log("Error for fecting the users", error)
            );
    }

    const handleSearchSchoolsByName = (e,name) => {
        e.preventDefault();
        axios.get(`http://localhost:8083/api/schools/search?`,{
            params : {name}
        })
            .then(response => {
                setSchools(response.data);
                console.log("data fetch successfully");
            })
            .catch(error =>
                console.log("Error for fecting the users", error)
            );
    }


    const handleEditSchool = (e,index) => {
        e.preventDefault();
        setEdit(true);

        setSchoolId(schools[index].id);
        setName(schools[index].name);
        setLocation(schools[index].location);
    }

    const handleDeleteSchool = (e,index) => {
        e.preventDefault();
        const schoolId = schools[index].id;
        axios.delete(`http://localhost:8083/api/schools/${schoolId}`)
        .then( response => {
            //alert("Data got Deleted")
            console.log("School got Deleteld");
            schoolList();
        })
        .catch(error => {
            console.error("Error occured",error);
        })
    }

    useEffect(() => {
        schoolList();
    },[]);

    const handleAddAndEditSchool = (e) =>  {
        e.preventDefault();
        if(edit) {
            axios.put(`http://localhost:8083/api/schools/${schoolId}`,{name:name,location:location})
            .then(response => {
            console.log("school got updated sucessfully");
            schoolList();
        })
        .catch(error => {
            console.error("Error occured",error);
        })
        setEdit(false);
        setSchoolId("");
        setName("");
        setLocation("");
        } 
        else {
        axios.post("http://localhost:8083/api/school",{name:name,location:location})
        .then(response => {
            console.log("school added sucessfully");
            schoolList();
        })
        .catch(error => {
            console.error("Error occured",error);
        })
    }
    }
    return (
        <div className="container mt-3">
            <h2>School List</h2>
            <br/>
            <h3>Show the List of Shools based on Loaction</h3>
               Search by Location : <input type="text" name="location" onChange={(e) => handleGetSchoolsByLocation(e,e.target.value)} />
            <br/>
            <br/>
            <h3>Show the List of Shools based on Name</h3>
             Search Schools By Name : <input type="text" name="name" onChange={(e) => handleSearchSchoolsByName(e,e.target.value)} />
            <br/>
            <br/>
            <button type="submit" onClick={handleClick} className="btn btn-primary">Show Schools </button>
            <br/>
            <br/>
            <form>
                <table border="4" align="center" className="container mt-1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {schools && schools.map((school,index) => (
                            <tr key={school.id}>
                                <td>{school.id}</td>
                                <td>{school.name}</td>
                                <td>{school.location}</td>
                                <td><button type="submit" onClick={(e) => handleEditSchool(e,index)} className="btn btn-primary"> Edit </button>&nbsp;&nbsp;&nbsp;
                                    <button type="submit" onClick={(e) => handleDeleteSchool(e,index)} className="btn btn-primary">Delete</button></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <br/>
                Name : <input type="text" className="form-control" value={name} onChange={(e) => setName(e.target.value)}/> <br/>
                Location : <input type="text" className="form-control" value={location} onChange={(e) => setLocation(e.target.value)}/> <br/>
                <br/>
               <button type="submit" onClick={handleAddAndEditSchool} className="btn btn-primary"> {edit ? "Update" : "Add"}</button> 
               <br/>
               <br/> 
            </form>
        </div>
    )
}

export default SchoolComponent;