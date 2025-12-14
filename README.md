# SOA E-Commerce Orchestrator

Academic project for the **Electronic Commerce** course at ITAM.
**Professor:** Rafael Gregorio Gamboa Hirales.

This system implements a Service-Oriented Architecture (SOA) to orchestrate a complete sales flow: Inventory Check, Credit Authorization, and Shipping Management.

## Architecture Overview

The system is composed of 3 Atomic Web Services (SOAP) and 1 Orchestrator Process (BPEL):

1.  **Inventory Service (WS):** Manages product stock (Derby DB).
2.  **Credit Service (WS):** Validates customer credit limits (Derby DB).
3.  **Shipping Service (WS):** Generates shipping orders upon successful purchase.
4.  **Orchestrator (BPEL):** Connects the services using logic gates:
    * Flow: Request -> Check Stock -> Check Credit -> (If OK) Ship -> (If Fail) Compensate/Rollback.

## Tech Stack (Legacy Environment)

* **IDE:** NetBeans 8.2 (Required for OpenESB plugins).
* **Server:** GlassFish Server 4.1.1.
* **Engine:** OpenESB Studio v3.1.2.3.
* **Database:** JavaDB (Apache Derby) on port 1527.
* **Protocols:** SOAP, WSDL, BPEL 2.0.

## Project Structure

* /Database_Scripts: SQL scripts to initialize the 3 Derby databases (populated with test data).
* /WebServices: Source code for the atomic SOAP services.
* /Orchestration: The BPEL process and the Composite Application (CA) for deployment.
* /Test_Clients: Java POJOs designed for Integration Testing and Load Testing (Stress Test).

## Setup & Deployment

1.  Start GlassFish 4.1.1 and the JavaDB Database.
2.  Run the scripts located in /Database_Scripts.
3.  Open the projects in NetBeans 8.2.
4.  Clean and Build the atomic Web Services.
5.  Deploy the Integrado_Bpel_Tienda_CA (Composite Application).
6.  Use the client in /Test_Clients to trigger a purchase flow.

## Stress Testing

The project includes a multi-threaded Load Tester (LanzadorEstres.java) capable of simulating concurrent users to measure Throughput (TPS) and Latency of the SOA infrastructure.

---
Developed by Bruno Daniel Pérez Vargas
