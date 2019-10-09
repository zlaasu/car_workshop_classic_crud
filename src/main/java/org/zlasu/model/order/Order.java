package org.zlasu.model.order;

import lombok.Getter;
import lombok.Setter;
import org.zlasu.model.MainModelInterface;
import org.zlasu.model.MainModel;

import java.util.Date;

@Getter
@Setter
public class Order extends MainModel implements MainModelInterface {

    private int id;
    private int customerId;
    private int vehicleId;
    private int statusId;
    private Date dateOrderAccepted;
    private Date dateRepairStart;
    private String problemDescription;
    private String repairDescription;
    private Double costRepair;
    private Double costParts;
    private Double costPerHour;
    private int numberOfManHours;

}
