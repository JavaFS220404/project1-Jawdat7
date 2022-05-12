package com.revature.models;

import java.sql.Timestamp;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
	private  Timestamp submitted;
    private Timestamp resolved;
    private String description;
   // private byte receipt;
  //  private int statusId;
   // private int typeId;
    private EnurmReim reimType;

    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
    }
  

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	//public int getStatusId() {
	//	return statusId;
	//}

	//public void setStatusId(int statusId) {
	//	this.statusId = statusId;
	//}

	//public int getTypeId() {
	//	return typeId;
	//}

	//public void setTypeId(int typeId) {
	//	this.typeId = typeId;
	//}

	public EnurmReim getReimType() {
		return reimType;
	}

	public void setReimType(EnurmReim reimType) {
		this.reimType = reimType;
	}

	
}
