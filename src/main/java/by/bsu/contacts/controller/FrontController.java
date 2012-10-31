package by.bsu.contacts.controller;

import by.bsu.contacts.command.*;
import by.bsu.contacts.controller.filters.*;
import by.bsu.contacts.dao.UnitOfWork;
import by.bsu.contacts.valuelisthandler.IteratorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontController {
    private IFilter beforeFilters;
    private IFilter afterFilters;
    private Map<String, Command> commandMap = new HashMap<String,Command>();

    public FrontController() {
        beforeFilters = new SubmitFilter(new BeastNumberAwareFilter(null));
        afterFilters = new AddHelloFilter(new AddWorldFilter(null));
        commandMap.put("create", new CreateContactCommand());
        commandMap.put("edit_first_name", new EditFirstNameCommand());
        commandMap.put("edit_last_name", new EditLastNameCommand());
        commandMap.put("edit_phone", new EditPhoneCommand());
        commandMap.put("list", new ListContactsCommand());
        commandMap.put("delete", new DeleteContactCommand());
        commandMap.put("error", new ErrorCommand());
    }

    public void process(Request request, Response response) throws IOException {
        try {
            UnitOfWork.newCurrent();
            if (!beforeFilters.doFilter(request, response)){
                return;
            }
            Command command = commandMap.get(request.getCommandName());
            if (command != null) {
                try {
                    try {
                        command.execute(request, response);
                    } catch (IteratorException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                    response.sendInAppRedirect("/error");
                }
            } else {
                if (!"favicon.ico".equals(request.getCommandName())) {
                    response.sendInAppRedirect("/error");
                }
            }
            afterFilters.doFilter(request, response);
            UnitOfWork.getCurrent().commit();
        } finally {
            UnitOfWork.setCurrent(null);
        }
    }


}
