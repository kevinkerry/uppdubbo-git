package com.csii.upp.transport.ws;

import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.oxm.UnmarshallingFailureException;

import com.csii.pe.channel.ws.ServiceManager;
import com.csii.pe.channel.ws.databinding.sdo.AbstractSdoMarshaller;
import com.csii.pe.channel.ws.extend.ExtendServiceManager;
import com.csii.pe.channel.ws.extend.databinding.sdo.ExtendSdoObjectFactory;

import commonj.sdo11.DataObject;

public class SdoMarshaller extends AbstractSdoMarshaller {
	public void setServiceManager(ServiceManager serviceManager) {
		if (serviceManager instanceof ExtendServiceManager) {
			super.setServiceManager(serviceManager);
		}
	}

	protected DataObject toSdo(String scope, QName alias, Map data) {
		ExtendSdoObjectFactory factory = ((ExtendServiceManager) getServiceManager())
				.getSdoFactory(scope);
		DataObject sdo = factory.createDataObject(alias);
		factory.toSdo(sdo, data);
		return sdo;
	}

	protected Map toMap(String scope, XMLStreamReader r) {
		ExtendSdoObjectFactory factory = ((ExtendServiceManager) getServiceManager())
				.getSdoFactory(scope);
		try {
			DataObject sdo = factory.load(r);

			Map map = factory.toData(sdo);
			return map;
		} catch (IllegalStateException e) {
			throw new UnmarshallingFailureException(e.getMessage(), e);
		} catch (XMLStreamException e) {
			throw new UnmarshallingFailureException(e.getMessage(), e);
		}
	}

	protected void writeSdoToXml(String scope, QName alias, DataObject sdo,
			XMLStreamWriter writer) throws XMLStreamException {
		ExtendSdoObjectFactory factory = ((ExtendServiceManager) getServiceManager())
				.getSdoFactory(scope);
		factory.save(alias, sdo, writer);
	}
}

