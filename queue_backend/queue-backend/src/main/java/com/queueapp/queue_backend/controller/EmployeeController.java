package com.queueapp.queue_backend.controller;

import com.queueapp.queue_backend.model.Employee;
import com.queueapp.queue_backend.model.QueueEntry;
import com.queueapp.queue_backend.model.dto.EmployeeLoginResponse;
import com.queueapp.queue_backend.service.EmployeeService;
import com.queueapp.queue_backend.service.QueueEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private QueueEntryService queueEntryService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<Employee> employee = employeeService.login(username, password);
        return employee.<ResponseEntity<?>>map(e -> {
            EmployeeLoginResponse response = new EmployeeLoginResponse(
                    e.getUsername(),
                    e.getCounterNumber(),
                    "Welcome, " + e.getUsername() + "! You are assigned to Counter " + e.getCounterNumber() + "."
            );
            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

    @GetMapping("/queue-list")
    public ResponseEntity<List<QueueEntry>> getQueue(@RequestParam String counterNumber) {
        List<QueueEntry> queueEntryList = employeeService.getWaitingQueue(counterNumber);
        return ResponseEntity.ok(queueEntryList);
    }

    @GetMapping("/peek-next-customer")
    public ResponseEntity<?> peekNextCustomer(@RequestParam String counterNumber) {
        Optional<QueueEntry> customer = queueEntryService.peekNextCustomer(counterNumber);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/next-customer")
    public ResponseEntity<QueueEntry> callNextCustomer(@RequestParam String counterNumber) {
        QueueEntry servedEntry = employeeService.serveNextCustomer(counterNumber);
        if (servedEntry != null) {
            return ResponseEntity.ok(servedEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
