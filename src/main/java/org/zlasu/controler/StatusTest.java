package org.zlasu.controler;

import org.zlasu.model.status.Status;
import org.zlasu.model.status.StatusDao;
import org.zlasu.util.crud.ModelInterface;

import java.util.List;

public class StatusTest {
    public static void main(String[] args) {

        Status status = new Status();
        StatusDao statusDao = new StatusDao();

        status.setName("test");
        status.setId(0);

        statusDao.create(status);

        Status statusRead = (Status) statusDao.readById(4);
        statusRead.setName("Status 4 new one");
        statusDao.update(statusRead);


        List<ModelInterface> list = statusDao.findAll();

        for (int i = 0; i < list.size(); i++) {
            Status statusItem = (Status) list.get(i);
            System.out.println(statusItem.getId() + " | " + statusItem.getName());
        }


//        System.out.println("statusRead = " + statusRead.getId());
//        System.out.println("statusRead = " + statusRead.getName());




        //statusDao.delete(statusRead);

    }
}
