package com.wpisen.trace.server.control.entity;

import java.util.Map;

/**
 * @author wpisen Created by wpisen on 2018/6/22
 **/
public class D3GraphData implements java.io.Serializable {
	private static final long serialVersionUID = 7961213331233577696L;
	private Node nodes[];
	private Relationship relationships[];

	public static class Node {
		private Integer id;
		private String labels[];
		private Map<String, String> properties;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String[] getLabels() {
			return labels;
		}

		public void setLabels(String[] labels) {
			this.labels = labels;
		}

		public Map<String, String> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}
	}

	public static class Relationship {
		private Integer id;
		private String type;
		private Integer startNode;
		private Integer endNode;
		private Map<String, String> properties;
		private Integer source;
		private Integer target;
		private Integer linknum;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getStartNode() {
			return startNode;
		}

		public void setStartNode(Integer startNode) {
			this.startNode = startNode;
		}

		public Integer getEndNode() {
			return endNode;
		}

		public void setEndNode(Integer endNode) {
			this.endNode = endNode;
		}

		public Map<String, String> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}

		public Integer getSource() {
			return source;
		}

		public void setSource(Integer source) {
			this.source = source;
		}

		public Integer getTarget() {
			return target;
		}

		public void setTarget(Integer target) {
			this.target = target;
		}

		public Integer getLinknum() {
			return linknum;
		}

		public void setLinknum(Integer linknum) {
			this.linknum = linknum;
		}
	}

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Relationship[] getRelationships() {
		return relationships;
	}

	public void setRelationships(Relationship[] relationships) {
		this.relationships = relationships;
	}
}
