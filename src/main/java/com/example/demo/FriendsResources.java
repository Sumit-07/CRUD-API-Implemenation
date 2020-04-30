package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendsResources {
	private FriendsRepository friend;
	FriendsResources()
	{
		this.friend=new FriendsRepository();
	}
@GetMapping("friend")
public List<friends> getfriend()
{
	//FriendsRepository friend=new FriendsRepository();
	return(friend.getAllFriends());
}
@GetMapping("friend/{id}")
public friends getFriendById(@PathVariable int id)
{
	//FriendsRepository friend=new FriendsRepository();
	return(friend.getOneFriend(id));
}
@PostMapping("friend")
public void postFriend(@RequestBody friends f)
{
	//FriendsRepository friend=new FriendsRepository();
	friend.putFriends(f);
	
}
@PutMapping("friend/{id}")
public void updateFriend(@PathVariable int id,@RequestBody friends newFriend)
{
	friend.updateFriend(id, newFriend);
}
@DeleteMapping("friend/{id}")
public void deleteFriend(@PathVariable int id)
{
	friend.removeFriend(id);
	
}
}
