package com.spring.series.mvc.rest.controller;

import com.spring.series.mvc.rest.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cricket")
public class MyRestController {

	// http://localhost:8080/SpringMVC-REST-4/cricket/getiplteam/MI

	/**
	 * Returns full-form of the IPL team in STRING format, based on the input arguments(iplcode)
	 * 
	 * @param iplcode
	 * @return
	 */
	@RequestMapping(value="/getiplteam/{iplcode}", method=RequestMethod.GET)
	public @ResponseBody String getIPLTeam(@PathVariable String iplcode) {

		if(null != iplcode && !"".equalsIgnoreCase(iplcode)){

			if("MI".equalsIgnoreCase(iplcode)){
				return "Mumbai Indians";
			}
			else if("DD".equalsIgnoreCase(iplcode)){
				return "Delhi Daredevils";
			}
			else{
				return "Chennai Super Kings";	
			}
		}
		else{
			return "Enter correct IPL team code";
		}
	}

	// http://localhost:8080/SpringMVC-REST-4/cricket/getplayer/1

	/**
	 * Returns Player OBJECT, based on the player id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getplayer/{id}", method=RequestMethod.GET)
	public @ResponseBody Player getPlayer(@PathVariable String id) {

		// create an object of type Player
		Player player = new Player();

		if(null != id && id.equalsIgnoreCase("1")){
			player.setId(1);
			player.setName("Sachin Tendulkar");
			player.setMatches("200");
		}
		else{
			player.setId(000);
			player.setName("International Player");
			player.setMatches("000");
		}
		return player;
	}

	// http://localhost:8080/SpringMVC-REST-4/cricket/createplayer

	@RequestMapping(value="/createplayer", method=RequestMethod.POST)
	public @ResponseBody String createOrSavePlayer(@RequestBody Player player) {
		// insert new record into database and return primary id ~ playerId
		int playerId = 0;
		String playerInfo = "New Player created/inserted with player id " + ++playerId;
		return playerInfo;
	}
}