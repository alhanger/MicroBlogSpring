package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by alhanger on 11/9/15.
 */
@Controller
public class MicroblogSpringController {

    @Autowired
    MessageRepository messages;

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("messages", messages.findAll());
        return "home";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping("/add-message")
    public String addMessage(String text) {
        Message message = new Message();
        message.text = text;

        messages.save(message);

        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteMessage(Integer id) {
        messages.delete(id);

        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String edit(Model model, Integer id) {

        model.addAttribute("message", messages.findOne(id));

        return "edit";
    }

    @RequestMapping("/edit-post")
    public String editMessage(Integer id, String editPost) {
        Message message = messages.findOne(id);

        message.text = editPost;

        messages.save(message);

        return "redirect:/";
    }
}
