package com.example.demo;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class FriendsResources {
	private static FriendsRepository friend=FriendsRepository.getInstance();
	Validator checkValidity=new Validator();
	private final Logger logger= LoggerFactory.getLogger(this.getClass());

@GetMapping("friend")
public List<friends> getfriend()
{
	logger.info("Fetching list of all friends...");
	return(friend.getAllFriends());
}
@GetMapping("friend/id={id}")
public friends getFriendById(@PathVariable int id) throws Exception
{	checkValidity.updateAndgetIdValidator(id);
	logger.info("Fetching friend by Id...");
	return(friend.getOneFriend(id));
}

@GetMapping("friend/name={name}")
public List<friends> getFriendByName(@PathVariable String name)
{
	checkValidity.lengthValidator(name);
	
	logger.info("Fetching friend by Name");

	if(name.length()==1)
	name=name+"%";
	else
	name="%"+name+"%";

	return(friend.getFriendsBy(name,"name"));

}

@GetMapping("friend/class={classId}")
public List<friends> getFriendByClass(@PathVariable String classId)
{
	if(classId.length()==1)
	classId=classId+"%";
	else
	classId="%"+classId+"%";
	
	return(friend.getFriendsBy(classId,"class"));
}

@GetMapping("friend/marks")
public List<Marksheet> getMarks()
{	logger.info("Getting friends marksheet!!");

	return(friend.getMarks());
}

@GetMapping("friend/marks/name={name}")
public List<Marksheet> getMarksByName(@PathVariable String name)
{
	checkValidity.lengthValidator(name);
	logger.warn("Revealing Personal marks!!");

	if(name.length()==1)
	name=name+"%";
	else
	name="%"+name+"%";
	
	return(friend.getMarksBy(name,"name"));
}

@GetMapping("friend/marks/id={id}")
public Marksheet getMarksById(@PathVariable int id) throws SQLException
{
	
	checkValidity.updateAndgetIdValidator(id);
	logger.warn("Revealing Personal marks!!");
	return(friend.getMarksById(id));
}

@GetMapping("friend/marks/class={class_id}")
public List<Marksheet> getMarksByClass(@PathVariable String class_id)
{
	checkValidity.lengthValidator(class_id);
	if(class_id.length()==1)
	class_id=class_id+"%";
	else
	class_id="%"+class_id+"%";
	
	return(friend.getMarksBy(class_id,"class"));
}

@PostMapping("friend")
public void postFriend(@RequestBody friends f) throws SQLException
{ 
	checkValidity.postValidator(f);
	checkValidity.idValidator(f.getId());
	friend.postFriends(f);
	
}

@PostMapping("friend/marks")
public void postMarks(@RequestBody Marksheet m)
{
	checkValidity.postValidator(m);
	friend.postMarks(m);
}


@PutMapping("friend")
public void updateFriend(@RequestBody friends newFriend) throws SQLException
{	checkValidity.postValidator(newFriend);
	checkValidity.updateAndgetIdValidator(newFriend.getId());
	logger.warn("Database is about to be updated");
	friend.updateFriend(newFriend);
}

@PutMapping("friend/marks")
public void updateMarks(@RequestBody Marksheet m)
{
	checkValidity.postValidator(m);
	friend.updateMarks(m);
}

@DeleteMapping("friend/id={id}")
public void deleteFriend(@PathVariable int id)
{	logger.warn("Data about to be deleted");
	friend.removeFriend(id);
	
}

}
