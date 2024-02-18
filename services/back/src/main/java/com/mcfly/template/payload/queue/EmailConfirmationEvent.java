package com.mcfly.template.payload.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class EmailConfirmationEvent {
    private String email;
    private String url;
}