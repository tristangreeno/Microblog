package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by tristangreeno on 5/4/16.
 */
@Controller
public class MicroblogSpringController {
    ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        model.addAttribute("name", session.getAttribute("username"));
        model.addAttribute("messages", session.getAttribute("messages"));
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username){
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "/create-message", method = RequestMethod.POST)
    public String add(HttpSession session, String text){
        messages.add(new Message(messages.size() + 1, text));
        session.setAttribute("messages", messages);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(HttpSession session, Integer id){
        messages.remove(id - 1);
        session.setAttribute("messages", messages);
        return "redirect:/";
    }
}
