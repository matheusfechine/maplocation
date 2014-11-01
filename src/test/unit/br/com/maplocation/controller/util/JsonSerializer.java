package br.com.maplocation.controller.util;

import java.io.Writer;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

public class JsonSerializer {

	private static final String DEFAULT_NEW_LINE = "";
	private static final char[] DEFAULT_LINE_INDENTER = {};
	private static final String INDENTED_NEW_LINE = "\n";
	private static final char[] INDENTED_LINE_INDENTER = { ' ', ' ' };
	private static boolean withoutRoot = false;
	private static boolean indented = false;
	
	private XStream xstream;
	
	public JsonSerializer(){
		this.xstream = getXStreamJSON();
	}
	
	private static XStream getXStreamJSON() {
		final String newLine = (indented ? INDENTED_NEW_LINE : DEFAULT_NEW_LINE);
		final char[] lineIndenter = (indented ? INDENTED_LINE_INDENTER : DEFAULT_LINE_INDENTER);
		
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver(){
			public HierarchicalStreamWriter createWriter(Writer writer) {
				if (withoutRoot) {
					return new JsonWriter(writer, lineIndenter, newLine, JsonWriter.DROP_ROOT_MODE);
				}
				return new JsonWriter(writer, lineIndenter, newLine);
			}
		});
		return xstream;
	}
	
	public String serialize(String alias, List<?> lista){
		xstream.alias(alias, List.class);
		return xstream.toXML(lista);
	}
	
	public String serialize(String alias, Object object){
		xstream.alias(alias, object.getClass());
		return xstream.toXML(object);
	}
	
	public String serialize(String alias, Exception object) {
		xstream.alias(alias, object.getClass());
		xstream.aliasAttribute("detailMessage", "message");
		xstream.omitField(Throwable.class, "stackTrace");
		xstream.omitField(Throwable.class, "suppressedExceptions");
		return xstream.toXML(object);
	}
	
}