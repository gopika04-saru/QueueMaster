package com.queueapp.queue_backend.service;

import com.queueapp.queue_backend.model.Employee;
import com.queueapp.queue_backend.model.QueueEntry;
import com.queueapp.queue_backend.model.dto.QueueEntryRequest;
import com.queueapp.queue_backend.model.dto.QueueEntryResponse;
import com.queueapp.queue_backend.repository.EmployeeRepository;
import com.queueapp.queue_backend.repository.QueueEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueueEntryService {

    @Autowired
    private QueueEntryRepository queueEntryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailNotificationService emailNotificationService;

    public QueueEntryResponse joinQueue(QueueEntryRequest request) {

        QueueEntry entry = new QueueEntry();
        entry.setFullName(request.getFullName());
        entry.setEmail(request.getEmail());
        entry.setPhoneNumber(request.getPhoneNumber());

        int nextToken = queueEntryRepository.findMaxTokenNumber().orElse(0) + 1;
        entry.setTokenNumber(nextToken);

        List<Employee> employees = employeeRepository.findAll();
        if (!employees.isEmpty()) {
            String assignedCounter = employees.get(nextToken % employees.size()).getCounterNumber();
            entry.setCounterAssigned(assignedCounter);
        } else {
            entry.setCounterAssigned("Unassigned");
        }

        entry.setCreatedAt(LocalDateTime.now());
        entry.setStatus("WAITING");

        queueEntryRepository.save(entry);

        emailNotificationService.sendTokenNotification(
                entry.getEmail(),
                entry.getTokenNumber(),
                entry.getCounterAssigned(),
                entry.getCreatedAt().toString()
        );
        return mapToResponse(entry);
    }

    public Optional<QueueEntry> peekNextCustomer(String counterNumber) {
        return queueEntryRepository.findFirstByCounterAssignedAndStatusOrderByTokenNumberAsc(counterNumber, "WAITING");
    }

    public List<QueueEntryResponse> getAllQueueEntries() {
        return queueEntryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public QueueEntryResponse getQueueEntryByToken(Integer tokenNumber) {
        QueueEntry entry = queueEntryRepository.findByTokenNumber(tokenNumber)
                .orElseThrow(() -> new RuntimeException("Token number not found"));

        return mapToResponse(entry);
    }

    public void completeEntryByToken(Integer tokenNumber) {
        QueueEntry entry = queueEntryRepository.findByTokenNumber(tokenNumber)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        entry.setStatus("SERVED");
        queueEntryRepository.save(entry);

        emailNotificationService.sendCompletionNotification(entry.getEmail(), entry.getTokenNumber());
    }

    private QueueEntryResponse mapToResponse(QueueEntry entry) {
        QueueEntryResponse response = new QueueEntryResponse();
        response.setFullName(entry.getFullName());
        response.setEmail(entry.getEmail());
        response.setPhoneNumber(entry.getPhoneNumber());
        response.setTokenNumber(entry.getTokenNumber());
        response.setCounterAssigned(entry.getCounterAssigned());
        response.setCreatedAt(entry.getCreatedAt());
        response.setStatus(entry.getStatus());
        return response;
    }
}
