package com.mony.bibliotecaDb4o.util;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.mony.bibliotecaDb4o.base.Socio;



public class Ficheros {
	
	public static void exportarSociosXml(List<Socio> listaDatos,String ruta) {
		 
		  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		  Document documento = null;

		  try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				DOMImplementation dom = builder.getDOMImplementation();
				documento = dom.createDocument(null,  "xml", null);
				
				Element raiz = documento.createElement("Socios");
				documento.getDocumentElement().appendChild(raiz);
				
				Element nodoSocio= null, nodoDatos = null;
				Text texto = null;
				for (Socio socio : listaDatos) {
					nodoSocio = documento.createElement("Socio");
					raiz.appendChild(nodoSocio);
					
					nodoDatos = documento.createElement("Codigo");
					nodoSocio.appendChild(nodoDatos);
					
					texto = documento.createTextNode(socio.getCodigo());
					nodoDatos.appendChild(texto);
					
					nodoDatos = documento.createElement("Nombre");
					nodoSocio.appendChild(nodoDatos);
					
					texto = documento.createTextNode(socio.getNombre());
					nodoDatos.appendChild(texto);
					
					nodoDatos = documento.createElement("Apellido");
					nodoSocio.appendChild(nodoDatos);
					
					texto = documento.createTextNode(socio.getApellido());
					nodoDatos.appendChild(texto);
					
					nodoDatos = documento.createElement("DNI");
					nodoSocio.appendChild(nodoDatos);
					
					texto = documento.createTextNode(socio.getDni());
					nodoDatos.appendChild(texto);
				}
				
				Source source = new DOMSource(documento);
				Result resultado = new StreamResult(new File(ruta));
				
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(source, resultado);
		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}
	
	public static void importarXML(String ruta){
		
		try{
		
		 List<Socio> socios = new ArrayList<Socio>();
		 Socio socio;
		 
		 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
         Document doc = docBuilder.parse (new File(ruta));

         doc.getDocumentElement ().normalize ();

         NodeList listaSocios = doc.getElementsByTagName("Socio");
         int totalSocios = listaSocios.getLength();
         System.out.println("Numero de socios : " + totalSocios);

         for(int s=0; s<listaSocios.getLength() ; s++){
        	 socio=new Socio();

             Node firstPersonNode = listaSocios.item(s);
             if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                 Element firstPersonElement = (Element)firstPersonNode;

                 //-------
                 NodeList firstNameList = firstPersonElement.getElementsByTagName("Codigo");
                 Element firstNameElement = (Element)firstNameList.item(0);
            
                 NodeList textFNList = firstNameElement.getChildNodes();
                 //
                 socio.setCodigo((textFNList.item(0)).getNodeValue().trim());
                 
                 System.out.println("Codigo : " + 
                        ((Node)textFNList.item(0)).getNodeValue().trim());

                 //-------
                 NodeList lastNameList = firstPersonElement.getElementsByTagName("Nombre");
                 Element lastNameElement = (Element)lastNameList.item(0);

                 NodeList textLNList = lastNameElement.getChildNodes();
                 socio.setNombre((textLNList.item(0)).getNodeValue().trim());
                 
                 System.out.println("Nombre : " + 
                        ((Node)textLNList.item(0)).getNodeValue().trim());

                 //----
                 NodeList ageList = firstPersonElement.getElementsByTagName("Apellido");
                 Element ageElement = (Element)ageList.item(0);

                 NodeList textAgeList = ageElement.getChildNodes();
                 socio.setApellido((textAgeList.item(0)).getNodeValue().trim());
                 System.out.println("Apellido : " + 
                        ((Node)textAgeList.item(0)).getNodeValue().trim());

                 //------
                 
                 //----
                 NodeList dni = firstPersonElement.getElementsByTagName("DNI");
                 Element dniElement = (Element)dni.item(0);

                 NodeList textDni = dniElement.getChildNodes();
                 socio.setDni((textDni.item(0)).getNodeValue().trim());
                 System.out.println("Dni : " + 
                        ((Node)textDni.item(0)).getNodeValue().trim());

                 //------
                 
                socios.add(socio);
                
                
             }
             
             System.out.println(socios.size());
             for(Socio c : socios)
             	Util.db.store(c);
             
             Util.db.commit();
             
         }
       
         
     }catch(SAXParseException err) {
     System.out.println ("** Parsing error" + ", line " 
          + err.getLineNumber () + ", uri " + err.getSystemId ());
     System.out.println(" " + err.getMessage ());

     }catch(SAXException e) {
     Exception x = e.getException ();
     ((x == null) ? e : x).printStackTrace ();

     }catch(Throwable t) {
     t.printStackTrace ();
     }
		 
	}
}
