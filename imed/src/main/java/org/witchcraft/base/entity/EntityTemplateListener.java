package org.witchcraft.base.entity;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

import org.cerebrum.domain.patient.Patient;
import org.cerebrum.domain.provider.Physician;


public class EntityTemplateListener {

	@PrePersist
	public void encode(EntityTemplate entity) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 20);
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(bos ));
		encoder.writeObject(entity.getEntity());
		encoder.close();
		System.out.println(" ecoded xml : " + new String(bos.toByteArray()));
		entity.setEncodedXml(new String(bos.toByteArray()));

		/*
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new ByteArrayInputStream(bos.toByteArray())));
		EntityTemplate<Patient> result = new EntityTemplate<Patient>();
		Patient patient = (Patient)decoder.readObject();
		result.setEntity(patient);
		decoder.close();
		System.out.println("fn " + result.getEntity().getDisplayName() + result.getEntity().getPrimaryPhysician().getFirstName());
		*/
	}
	
	@PostLoad
	public void decode(EntityTemplate entity){
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new ByteArrayInputStream(entity.getEncodedXml().getBytes()) ));
		entity.setEntity((BusinessEntity) decoder.readObject());
		decoder.close();
	}
	
	public static void main(String args[]){
		Patient patient = new Patient();
		patient.setFirstName("suls");
		patient.setLastName("muls");
		EntityTemplate template = new EntityTemplate<Patient>();
		Physician phys = new Physician();
		phys.setFirstName("dr derek");
		phys.setLastName("ekers");
		patient.setPrimaryPhysician(phys);
		template.setEntityName("org.cerbrum.patient");
		template.setEntity(patient);
		new EntityTemplateListener().encode(template);
	}

}
