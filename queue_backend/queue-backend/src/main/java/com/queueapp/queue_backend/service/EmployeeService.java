package com.queueapp.queue_backend.service;

import com.queueapp.queue_backend.model.Employee;
import com.queueapp.queue_backend.model.QueueEntry;
import com.queueapp.queue_backend.repository.EmployeeRepository;
import com.queueapp.queue_backend.repository.QueueEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private QueueEntryRepository queueEntryRepository;

    public Optional<Employee> login(String username, String password) {
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        return employee.filter(e -> e.getPassword().equals(password));
    }

    public List<QueueEntry> getWaitingQueue(String counterNumber) {
        return queueEntryRepository.findByCounterAssignedAndStatusOrderByTokenNumberAsc(counterNumber, "WAITING");
    }

    public QueueEntry serveNextCustomer(String counterNumber) {
        Optional<QueueEntry> next = queueEntryRepository
                .findTopByCounterAssignedAndStatusOrderByTokenNumberAsc(counterNumber, "WAITING");

        if (next.isPresent()) {
            QueueEntry entry = next.get();
            entry.setStatus("SERVED");
            return queueEntryRepository.save(entry);
        } else {
            System.out.println("No waiting customers found at counter: " + counterNumber);
        }
        return null;
    }
}
